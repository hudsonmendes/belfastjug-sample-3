import numpy
from datetime import datetime
from tabulate import tabulate

MAX_ROWS = 1000
MAX_COLUMNS = 10
GP = 10

def main():
    print('running tests...')
    results = []
    rows = 1
    columns = 1
    while rows <= MAX_ROWS and columns <= MAX_COLUMNS:
        dimensions = { 'rows': rows, 'columns': columns }
        results.append(benchmark(dimensions))
        columns *= GP
        rows *= GP
    print('tabulating results...')
    print(tabulate(results, headers=benchmark_header(), tablefmt='presto'))
    
def matrix_add_serial(a, b, dimensions):
    columns = dimensions['columns']
    rows = dimensions['rows']
    ret = [[0] * columns] * rows
    for i in range(0, rows):
        for j in range(1, columns):
            ret[i][j] = a[i][j] + b[i][j]
    return ret
    
def matrix_add_vector(a, b, dimensions):
    return numpy.add(a, b)
    
def benchmark_header():
    return [
        "dimensions",
        "elapsedms_serial",
        "elapsedms_vector",
        "checksum",
        "match"
    ]
        
def benchmark(dimensions):
    a = numpy.random.randn(dimensions['rows'], dimensions['columns'])
    b = numpy.random.randn(dimensions['rows'], dimensions['columns'])
    timed_serial = timed(lambda: matrix_add_serial(a, b, dimensions))
    timed_vector = timed(lambda: matrix_add_vector(a, b, dimensions))
    checksum_serial = checksum(timed_serial['result'], dimensions)
    checksum_vector = checksum(timed_vector['result'], dimensions)
    
    return [
        f'm: {str(dimensions["columns"]).rjust(3)} x n: {str(dimensions["rows"]).rjust(5)}',
        f'{timed_serial["elapsed_in_ms"]}ms',
        f'{timed_vector["elapsed_in_ms"]}ms',
        checksum_serial,
        "✅" if checksum_serial == checksum_vector else "🔴" ]

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
main()