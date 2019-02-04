package com.tels.assignment.model;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Transformers {

    @SerializedName("transformers")
    private List<Transformer> transformers = null;

    public List<Transformer> getTransformers() {
        return transformers;
    }

    public void setTransformers(List<Transformer> transformers) {
        this.transformers = transformers;
    }

}