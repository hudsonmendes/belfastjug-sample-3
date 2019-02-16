import numpy
from datetime import datetime
from tabulate import tabulate
from colorama import Fore, Style

DIMENSIONS = [
    { 'rows':       100, 'columns': 10 },
    { 'rows':      1000, 'columns': 10 },
    { 'rows':     10000, 'columns': 10 },
    { 'rows':    100000, 'columns': 10 },
    { 'rows':   1000000, 'columns': 10 },
    { 'rows':  10000000, 'columns': 10 },
]

def main():
    print('running tests...')
    results = []
    for dimensions in DIMENSIONS:
        benckmark_dimensions(dimensions)
        results.append(benchmark(dimensions))
    print('tabulating results...')
    benchmark_print(results)
    
def matrix_add_serial(a, b, dimensions):
    columns = dimensions['columns']
    rows = dimensions['rows']
    ret = numpy.zeros((rows, columns))
    for i in range(0, rows):
        for j in range(0, columns):
            ret[i][j] = a[i][j] + b[i][j]
    return ret
    
def matrix_add_vector(a, b, dimensions):
    return numpy.add(a, b)
    
def benchmark_header():
    return [
        "dimensions",
        "elapsed_in_ms (serial)",
        "elapsed_in_ms (vector)",
        "faster vector",
        "checksum",
        "match"
    ]
    
def benckmark_dimensions(dimensions):
    dimensions_as_s = f'{dimensions["rows"]} x {dimensions["columns"]}'
    print(f'benchmarking: {dimensions_as_s.rjust(13)}')
        
def benchmark(dimensions):
    a = numpy.random.randn(dimensions['rows'], dimensions['columns'])
    b = numpy.random.randn(dimensions['rows'], dimensions['columns'])
    timed_serial = timed(lambda: matrix_add_serial(a, b, dimensions))
    timed_vector = timed(lambda: matrix_add_vector(a, b, dimensions))
    checksum_serial = checksum(timed_serial['result'], dimensions)
    checksum_vector = checksum(timed_vector['result'], dimensions)
    faster_in_ms = timed_vector["elapsed_in_ms"] - timed_serial["elapsed_in_ms"]
    
    return [
        f'{str(dimensions["rows"])} x {str(dimensions["columns"])}',
        f'{timed_serial["elapsed_in_ms"]}ms',
        f'{timed_vector["elapsed_in_ms"]}ms',
        green(f'{faster_in_ms}ms'),
        checksum_serial,
        "✅" if checksum_serial == checksum_vector else "🔴" ]
    
def benchmark_print(results):
    print(tabulate(
        results,
        tablefmt='presto',
        colalign=('right', 'right', 'right', 'right',),
        headers=benchmark_header(),))

def checksum(vector, dimensions):
    columns = dimensions['columns']
    rows = dimensions['rows']
    total = 0
    for i in range(0, rows):
        for j in range(0, columns):
            total += vector[i][j]
    return total

def timed(proc):
    tic = datetime.now()
    ret = proc()
    toc = datetime.now()
    elapsed_in_ms = int((toc - tic).microseconds / 1000)
    return { 'result': ret, 'elapsed_in_ms': elapsed_in_ms }

def green(exp):
    return Fore.GREEN + exp + Style.RESET_ALL

main()