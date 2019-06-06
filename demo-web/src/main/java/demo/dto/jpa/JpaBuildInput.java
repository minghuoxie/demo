package demo.dto.jpa;

import demo.demodao.DemoJpaBuild;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class JpaBuildInput {
    @ApiModelProperty(value = "年", required = true)
    @NotNull(message = "年")
    private int year;

    @ApiModelProperty(value = "季度", required = true)
    @NotNull(message = "季度")
    private String quarter;

    @ApiModelProperty(value = "jpadatas", required = true)
    @NotNull(message = "jpadatas")
    private List<DemoJpaBuild> builds;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public List<DemoJpaBuild> getBuilds() {
        return builds;
    }

    public void setBuilds(List<DemoJpaBuild> builds) {
        this.builds = builds;
    }
}
