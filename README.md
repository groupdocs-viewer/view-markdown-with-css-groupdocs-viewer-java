# GroupDocs Viewer Java – View Markdown with CSS

## Overview
This showcase project demonstrates how to use **GroupDocs Viewer** (Java) to render a Markdown file to HTML and save the stylesheet as a separate resource. It is a minimal, runnable Maven project that helps developers understand how to view a `.md` file with the CSS produced by the library.

## Prerequisites
- **Java Development Kit (JDK) 8** or higher
- **Apache Maven** 3.5+ installed and added to your `PATH`
- An **Internet connection** for Maven to download dependencies

## License
GroupDocs Viewer requires a license file to work without evaluation limitations (watermarks, page limits, etc.).

1. **Obtain a temporary license** – Visit https://purchase.groupdocs.com/temporary-license/ to get a free 30‑day temporary license.
2. **Place the license file** – Save the downloaded `GroupDocs.Viewer.Java.lic` file in the **project root directory** (the same level where `pom.xml` resides).
3. **Without a license** – The library will run in evaluation mode which adds watermarks to rendered pages and may impose other restrictions.

## Project Structure
```
view-markdown-with-css-groupdocs-viewer-java/
│   pom.xml
│   README.md
│   GroupDocs.Viewer.Java.lic   (optional – place your license here)
│
├───src
│   └───main
│       └───java
│           └───com
│               └───groupdocs
│                   └───viewer
│                       └───examples
│                           └───ViewMarkdownWithCssExample.java
│
├───resources
│   ├───input
│   │   └───sample.md            (sample Markdown – replace with your own)
│   └───output                   (generated HTML pages and CSS will be saved here)
```

## Setup & Run
1. **Clone or copy the project**
   ```bash
   git clone https://github.com/your-repo/view-markdown-with-css-groupdocs-viewer-java.git
   cd view-markdown-with-css-groupdocs-viewer-java
   ```
2. **Add your license file** (optional but recommended) – copy `GroupDocs.Viewer.Java.lic` into the project root.
3. **Place a Markdown file** you want to render inside `resources/input/`. The project already contains `sample.md`, which you can replace.
4. **Build the project**
   ```bash
   mvn clean compile
   ```
5. **Run the example**
   - Using Maven Exec plugin:
     ```bash
     mvn exec:java
     ```

## What Happens When You Run It?
- The program loads the license (if present).
- It checks `resources/input/sample.md`. If the file is missing, execution stops with an error message.
- An output directory `resources/output/` is created if it does not already exist.
- The file is loaded as Markdown (`FileType.MD`) and rendered to HTML.
- Stylesheets are written next to the pages, with names like `page_1/resource_1_*.css`. Responsive rendering is enabled.
- Console output informs you about the progress and any possible errors.

## Customization
- **Embed the stylesheet in the HTML** – replace `HtmlViewOptions.forExternalResources(...)` with `HtmlViewOptions.forEmbeddedResources(pageFilePathFormat)`.
- **Change the font** – call `options.setDefaultFontName("Arial")` before rendering.
- **Render another document** – replace `resources/input/sample.md` with another Markdown file, or change `INPUT_FILE` in the source.
- **Render a page range** – call `viewer.view(options, 1, 2)` to render the first two pages instead of the whole document.

## Notes
- The included `sample.md` is a short Markdown document, so HTML rendering and the generated stylesheet can be checked immediately.
- Markdown is treated as a text format. Pass `FileType.MD` in `LoadOptions` when you open the file.
- Ensure the `resources/input/` and `resources/output/` directories are at the same level as `pom.xml` as shown in the structure above.

---

*This is a showcase project for GroupDocs Viewer. It is intended for demonstration and educational purposes.*
