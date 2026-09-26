package com.groupdocs.viewer.examples;

import com.groupdocs.viewer.FileType;
import com.groupdocs.viewer.License;
import com.groupdocs.viewer.Viewer;
import com.groupdocs.viewer.options.HtmlViewOptions;
import com.groupdocs.viewer.options.LoadOptions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Demonstrates how to render a Markdown file to HTML with a stylesheet using GroupDocs Viewer.
 * <p>
 * The example expects a Markdown file (<code>sample.md</code>) placed in the <code>resources/input/</code>
 * directory (project root). Rendered HTML pages and their CSS resources are saved to <code>resources/output/</code>.
 * </p>
 */
public class ViewMarkdownWithCssExample {

    private static final String INPUT_FILE = "resources/input/sample.md";
    private static final String OUTPUT_DIR = "resources/output/";
    private static final String LICENSE_FILE = "GroupDocs.Viewer.Java.lic";

    /**
     * Loads the GroupDocs Viewer license if the license file exists.
     * <p>
     * To get a temporary license, visit:
     * <a href="https://purchase.groupdocs.com/temporary-license/">https://purchase.groupdocs.com/temporary-license/</a>.
     * Place the downloaded <code>GroupDocs.Viewer.Java.lic</code> file in the project root directory.
     * Without a license the library works in evaluation mode with watermarks and other limitations.
     * </p>
     *
     * @param licensePath path to the license file relative to the project root
     */
    public static void loadLicense(String licensePath) {
        File licenseFile = new File(licensePath);
        if (licenseFile.exists()) {
            try {
                License license = new License();
                license.setLicense(licensePath);
                System.out.println("GroupDocs Viewer license loaded successfully.");
            } catch (Exception e) {
                System.err.println("Failed to load GroupDocs Viewer license: " + e.getMessage());
            }
        } else {
            System.out.println("License file not found at " + licensePath + ". Running in evaluation mode.");
        }
    }

    /**
     * Renders the Markdown file to HTML and writes the stylesheet as an external resource.
     * <p>
     * Markdown is a text format, so the method sets {@link FileType#MD} on {@link LoadOptions}.
     * HTML pages are saved with the pattern <code>page_{index}.html</code>. Stylesheets and other
     * resources are saved beside them. Responsive rendering is enabled so the generated CSS
     * adapts the page layout.
     * </p>
     */
    public static void renderMarkdownWithCss() {
        Path outDirPath = Paths.get(OUTPUT_DIR);
        try {
            if (!Files.exists(outDirPath)) {
                Files.createDirectories(outDirPath);
                System.out.println("Created output directory: " + outDirPath.toAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Failed to create output directory: " + e.getMessage());
            return;
        }

        File inputFile = new File(INPUT_FILE);
        if (!inputFile.exists()) {
            System.err.println("Input file not found: " + INPUT_FILE);
            return;
        }

        String pageFilePathFormat = outDirPath.resolve("page_{0}.html").toString();
        String resourceFilePathFormat = outDirPath.resolve("page_{0}").resolve("resource_{0}_{1}").toString();
        String resourceUrlFormat = "page_{0}/resource_{0}_{1}";

        // Text formats such as Markdown need an explicit file type.
        LoadOptions loadOptions = new LoadOptions(FileType.MD);
        try (Viewer viewer = new Viewer(INPUT_FILE, loadOptions)) {
            HtmlViewOptions options = HtmlViewOptions.forExternalResources(
                    pageFilePathFormat, resourceFilePathFormat, resourceUrlFormat);
            options.setRenderResponsive(true);
            viewer.view(options);
            System.out.println("Document rendered successfully. Output saved to: " + outDirPath.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error during rendering: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Load license if available
        loadLicense(LICENSE_FILE);
        // Perform the rendering demonstration
        renderMarkdownWithCss();
    }
}
