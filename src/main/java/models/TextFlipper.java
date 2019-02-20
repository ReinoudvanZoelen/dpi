package models;

import interfaces.ITextProcessor;

public class TextFlipper implements ITextProcessor {
    @Override
    public String Process(String message) {
        return new StringBuilder(message).reverse().toString();
    }
}
