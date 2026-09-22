package com.groupdocs.viewer.examples;

import com.groupdocs.viewer.Viewer;
import com.groupdocs.viewer.options.HtmlViewOptions;
import com.groupdocs.viewer.options.LoadOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demonstrates rendering Markdown documents to HTML with real-time CSS styling using GroupDocs Viewer for Java.
 * <p>
 * Key features:
 * <ul>
 *   <li>Load a Markdown file from local disk</li>
 *   <li>Render to HTML with embedded CSS resources</li>
 *   <li>Apply custom CSS styling via HtmlViewOptions</li>
 *   <li>Save output to the resources/output directory</li>
 * </ul>
 */
public class ViewMarkdownWithCssExample {

    public static void main(String[] args) {
        applyLicense();
        run();
    }

    /**
     * Applies a temporary or licensed key to unlock full functionality.
     * If no license file is found, the component runs in evaluation mode.
     * Get a free 30-day temporary license: https://purchase.groupdocs.com/temporary-license
     */
    private static void applyLicense() {
        String licensePath = "src/main/resources/groupdocs.viewer.lic";
        File licenseFile = new File(licensePath);
        if (licenseFile.exists()) {
            try {
                com.groupdocs.viewer.License license = new com.groupdocs.viewer.License();
                license.setLicense(licensePath);
                System.out.println("License applied successfully.");
            } catch (Exception e) {
                System.err.println("Failed to apply license: " + e.getMessage());
            }
        } else {
            System.out.println("No license file found. Running in evaluation mode.");
        }
    }

    /**
     * Main demo method: renders a Markdown file to HTML with custom CSS styling.
     */
    public static void run() {
        // Input and output paths
        String inputPath = "src/main/resources/input/sample.md";
        String outputDir = "src/main/resources/output";

        // Ensure output directory exists
        try {
            Files.createDirectories(Paths.get(outputDir));
        } catch (IOException e) {
            System.err.println("Failed to create output directory: " + e.getMessage());
            return;
        }

        // -----------------------------------------------------------------
        // Step 1. Load the Markdown file using Viewer constructor.
        // -----------------------------------------------------------------
        try (Viewer viewer = new Viewer(inputPath)) {

            // -----------------------------------------------------------------
            // Step 2. Configure HTML rendering options with embedded CSS resources.
            // -----------------------------------------------------------------
            HtmlViewOptions viewOptions = HtmlViewOptions.forEmbeddedResources();

            // -----------------------------------------------------------------
            // Step 3. Apply custom CSS styling to the rendered HTML output.
            // -----------------------------------------------------------------
            viewOptions.setCssResourcesPrefix("css/");
            viewOptions.setResourceLoadingTimeout(30);

            // Inject custom CSS content (e.g., Markdown-specific styling)
            String customCss = "body { font-family: 'Segoe UI', sans-serif; line-height: 1.6; } " +
                    "h1, h2, h3 { color: #2c3e50; } " +
                    "pre { background-color: #f4f4f4; padding: 10px; border-radius: 4px; } " +
                    "code { background-color: #e8e8e8; padding: 2px 5px; border-radius: 3px; }";
            viewOptions.setAdditionalHtmlContent("<style>" + customCss + "</style>");

            // -----------------------------------------------------------------
            // Step 4. Render the Markdown document to HTML and save to disk.
            // -----------------------------------------------------------------
            String outputPath = outputDir + "/sample.html";
            viewer.view(viewOptions, outputPath);

            System.out.println("Markdown file rendered to HTML with CSS styling at: " + outputPath);

        } catch (Exception e) {
            System.err.println("Error rendering Markdown file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
