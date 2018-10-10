package gr.aueb.android.barista.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miltiadis Konsolakis on 14/12/2015.
 *
 * Annotation mesa apo to opoio dilonoume Modela siskeuon to default orientation einai PORTRAIT
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})

public @interface Device {

    public enum Model {
        NEXUS_S(240, 480, 800),
        NEXUS_4(320, 768, 1280),
        NEXUS_5(480, 1080, 1920),
        NEXUS_6(640, 1440, 2560),

        SAMSUNG_GALAXY_S6(640, 1440, 2560),
        SAMSUNG_GALAXY_NOTE_4(480, 1440, 2560),
        SAMSUNG_GALAXY_TAB_10(160, 800, 1280),

        LG_G2(480, 1080, 1920),
        LG_G3(480, 1440, 2560),

        SONY_XPERIA_Z3(320, 720, 1280);


        int dpi;
        int width;
        int height;

        Model(int dpi, int width, int height) {
            this.dpi = dpi;
            this.width = width;
            this.height = height;
        }

        public int getDpi() { return dpi; }
        public int getWidth() { return width; }
        public int getHeight() { return  height; }

        /*public Configuration getConfiguration() {
            return new Configuration(){
                @Override
                public Class<? extends Annotation> annotationType() { return Configuration.class; }

                @Override
                public int dpi() { return dpi; }

                @Override
                public int width() { return width; }

                @Override
                public int height() { return height; }

                @Override
                public Orientation orientation() { return orientation1(); }
            };
        }*/
    }

    Model model() default Model.NEXUS_S;
    Configuration.Orientation orientation() default Configuration.Orientation.PORTRAIT;
}
