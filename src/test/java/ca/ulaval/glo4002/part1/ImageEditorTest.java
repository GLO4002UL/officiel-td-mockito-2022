package ca.ulaval.glo4002.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageEditorTest {
    private static final String AN_IMAGE = "potato";

    private ImageEditor imageEditor;

    @BeforeEach
    void before() {
        imageEditor = new ImageEditor(null /* new ResourcesImageLoader() ?? */);
    }

    @Test
    void whenLoadingAnImage_thenLoadsImageFromTheRightDirectory() {
        imageEditor.renderImageByName(AN_IMAGE);

        // ???, sans mockito
    }

    @Test
    void whenLoadingAnImage_thenLoadsImageFromTheRightDirectory_withMockito() {
        imageEditor.renderImageByName(AN_IMAGE);

        // verify(...)
    }
}
