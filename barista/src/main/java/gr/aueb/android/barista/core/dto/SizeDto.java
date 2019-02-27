/**
 * Author: Tsiskomichelis Stelios
 * Created On: 22/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: SizeDto
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.dto;


@Deprecated
public class SizeDto {

    private int width;

    private int height;

    private int dpi;

    public SizeDto() {

    }

    public SizeDto(int width, int height, int dpi) {
        this.width = width;
        this.height = height;
        this.dpi = dpi;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
}
