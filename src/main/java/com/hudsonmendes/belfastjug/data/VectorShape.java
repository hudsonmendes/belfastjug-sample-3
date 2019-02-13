package com.hudsonmendes.belfastjug.data;

public class VectorShape {
    private final int x;
    private final int y;

    private VectorShape(final Builder builder) {
        x = builder.x;
        y = builder.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builderFrom(final VectorShape vectorShape) {
        return new Builder(vectorShape);
    }

    public static final class Builder {
        private int x;
        private int y;

        private Builder() {}

        private Builder(final VectorShape vectorShape) {
            x = vectorShape.x;
            y = vectorShape.y;
        }

        public Builder withX(final int x) {
            this.x = x;
            return this;
        }

        public Builder withY(final int y) {
            this.y = y;
            return this;
        }

        public VectorShape build() {
            return new VectorShape(this);
        }
    }
}
