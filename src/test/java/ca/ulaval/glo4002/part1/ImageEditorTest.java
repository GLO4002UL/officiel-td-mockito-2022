package ca.ulaval.glo4002.part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ImageEditorTest {
    private static final String AN_IMAGE = "potato";
    private static final String EXPECTED_PATH = "/tmp/part1-images/potato.png";

    private ImageEditor imageEditor;

    @Test
    void whenLoadingAnImage_thenLoadsImageFromTheRightDirectory() {
        TestImageLoader imageLoader = new TestImageLoader();
        imageEditor = new ImageEditor(imageLoader);

        imageEditor.renderImageByName(AN_IMAGE);

        assertEquals(EXPECTED_PATH, imageLoader.lastLoadedName);
    }

    @Test
    void whenLoadingAnImage_thenLoadsImageFromTheRightDirectory_withMockito() {
        ImageLoader imageLoader = mock(ImageLoader.class);
        imageEditor = new ImageEditor(imageLoader);

        imageEditor.renderImageByName(AN_IMAGE);

        verify(imageLoader).load(EXPECTED_PATH);
    }
}

class TestImageLoader implements ImageLoader {
    public String lastLoadedName = null;

    @Override
    public byte[] load(String name) {
        lastLoadedName = name;

        return new byte[0];
    }
}
