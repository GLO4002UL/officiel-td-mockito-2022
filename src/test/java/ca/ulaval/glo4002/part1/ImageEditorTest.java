package ca.ulaval.glo4002.part1;

import org.junit.jupiter.api.Test;

class ImageEditorTest {
    private static final String AN_IMAGE = "potato";

    private ImageEditor imageEditor;

    @Test
    void whenLoadingAnImage_thenLoadsImageFromTheRightDirectory() {
        imageEditor = new ImageEditor(null /* new ResourcesImageLoader() ?? */);

        imageEditor.renderImageByName(AN_IMAGE);

        // ???, sans mockito
    }

    @Test
    void whenLoadingAnImage_thenLoadsImageFromTheRightDirectory_withMockito() {
        imageEditor = new ImageEditor(null /* new ResourcesImageLoader() ?? */);

        imageEditor.renderImageByName(AN_IMAGE);

        // verify(...)
    }
}
