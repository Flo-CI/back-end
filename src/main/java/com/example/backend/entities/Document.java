package com.example.backend.entities;

public interface Document {
    public String[] getAllData();

    public String getDocumentType();

    public boolean validateDocument();
}
