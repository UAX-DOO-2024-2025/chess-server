package com.uax.chess.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;

public abstract class Ficha implements Color, Comparable<Ficha>, JsonSerializable {
    private TiposColor color;

    public Ficha(TiposColor color) {
        this.color = color;
    }

    @Override
    public void setColor(TiposColor color) {
        this.color = color;
    }

    @Override
    public TiposColor getColor() {
        return color;
    }

    public abstract boolean validarMovimiento(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino,
            Tablero tablero);

    public abstract char obtenerRepresentacion();

    @Override
    public String toString() {
        return "Ficha: " + obtenerRepresentacion() + " Color: " + color;
    }

    @Override
    public int compareTo(Ficha o) {
        if (this.color != o.color) {
            return this.color == TiposColor.BLANCO ? -1 : 1;
        }
        return Integer.compare(getOrdenPrioridad(), o.getOrdenPrioridad());
    }

    protected abstract int getOrdenPrioridad();

    public void mover() {
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("color", color.toString());
        jsonGenerator.writeStringField("representacion", String.valueOf(obtenerRepresentacion()));
        jsonGenerator.writeEndObject();
    }

    @Override
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        serialize(jsonGenerator, serializerProvider);
    }
}
