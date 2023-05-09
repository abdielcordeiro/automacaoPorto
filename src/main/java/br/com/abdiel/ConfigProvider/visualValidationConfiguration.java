package br.com.abdiel.ConfigProvider;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class visualValidationConfiguration {
    private String baselineFolder;
    private boolean failIfNoBaselineFound;
}
