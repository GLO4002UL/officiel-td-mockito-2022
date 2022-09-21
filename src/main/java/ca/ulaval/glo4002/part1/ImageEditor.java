package ca.ulaval.glo4002.part1;

public class ImageEditor {
    private static final String IMAGES_PATH = "/tmp/part1-images/";
    private ImageLoader imageLoader;

    public ImageEditor(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public byte[] renderImageByName(String name) {
        return imageLoader.load(IMAGES_PATH + name + ".png");
    }
}
