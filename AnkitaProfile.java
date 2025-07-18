import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class AnkitaProfile extends JFrame {
    private static final String[] CONTENT_SECTIONS = {
       
          "<html><div style='width:400px;padding:10px;color:#333;'><b style='color:#4a6baf;font-size:16px;'>🎓 Intro</b><br><br>" +
        "• <b><i>Student at Bapatla Engineering College<br>" +
        "• <b><i>I am passionate about pursuing a career in data analysis and web development, where I can apply my technical skills in Python, machine learning, and full-stack web technologies to solve real-world problems. </i><br></div></html>",
        "<html><div style='width:400px;padding:10px;color:#333;'><b style='color:#4a6baf;font-size:16px;'>🎓 Education</b><br><br>" +
        "• <b>B.Tech IT</b> - Bapatla Engineering College (2022-Pursuing)<br>" +
        "• <b>MPC</b> - St. Ann's Junior College (2020-2022)<br>" +
        "• <b>SSC</b> - Sri Satya Sai High School (2020)</div></html>",
        
        "<html><div style='width:400px;padding:10px;color:#333;'><b style='color:#4a6baf;font-size:16px;'>💼 Internships</b><br><br>" +
        "• <b>Data Science Intern</b> @ BIST Technologies (May-Jun 2024)<br>" +
        "&nbsp;&nbsp;- Built credit card fraud detection model<br>" +
        "• <b>Python Intern</b> @ Vault of Codes (May-Jun 2024)<br>" +
        "&nbsp;&nbsp;- Developed automation scripts<br>" +
        "• <b>Web Dev Intern</b> @ Topper World (Apr-May 2024)<br>" +
        "&nbsp;&nbsp;- Created responsive websites</div></html>",
        
        "<html><div style='width:400px;padding:10px;color:#333;'><b style='color:#4a6baf;font-size:16px;'>🧠 Technical Skills</b><br><br>" +
        "• <b>Programming:</b> Python, Java, C, JavaScript<br>" +
        "• <b>Web:</b> HTML5, CSS3, JavaScript, Git<br>" +
        "• <b>Data Science:</b> Pandas, Scikit-learn<br>" +
        "• <b>Other:</b> DSA, MS Office</div></html>",
        
        "<html><div style='width:400px;padding:10px;color:#333;'><b style='color:#4a6baf;font-size:16px;'>📂 Key Project</b><br><br>" +
        "<b>Credit Card Fraud Detection</b> (June 2024)<br>" +
        "• ML model for fraud detection<br>" +
        "• Python with Pandas/Scikit-learn<br>" +
        "• SMOTE for imbalanced data<br>" +
        "• High accuracy results</div></html>",
        
        "<html><div style='width:400px;padding:10px;color:#333;'><b style='color:#4a6baf;font-size:16px;'>🏅 Certifications</b><br><br>" +
        "• Cloud Computing - NPTEL<br>" +
        "• Responsive Web Design - freeCodeCamp<br>" +
        "• Artificial Intelligence - IBM<br>" +
        "• Python Essentials - Cisco</div></html>",
        
        "<html><div style='width:400px;padding:10px;color:#333;'><b style='color:#4a6baf;font-size:16px;'>📫 Contact</b><br><br>" +
        "• <b>Email:</b> gudipudiankita@gmail.com<br>" +
        "• <b>Phone:</b> +91-97059-30168<br>" +
        "• <b>GitHub:</b> github.com/gudipudiankita<br>" +
        "• <b>LinkedIn:</b> linkedin.com/in/gudipudiankita</div></html>"
    };

    private int currentIndex = 0;
    private JLabel contentLabel;
    private static final String IMAGE_PATH = "C:\\Users\\ggudi\\OneDrive\\Desktop\\photo3.jpg";

    public AnkitaProfile() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Ankita Gudipudi - Professional Profile");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(248, 248, 248));

        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(248, 248, 248));

        // Photo Panel
        JPanel photoPanel = createPhotoPanel();
        mainPanel.add(photoPanel, BorderLayout.WEST);

        // Content Panel
        JPanel contentPanel = createContentPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // Footer with navigation
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);

        // Start content rotation
        startContentRotation();

        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(235, 241, 251));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JLabel nameLabel = new JLabel("ANKITA GUDIPUDI");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        nameLabel.setForeground(new Color(74, 107, 175));
        
        panel.add(nameLabel);
        return panel;
    }

    private BufferedImage getHighQualityScaledImage(BufferedImage original, int targetWidth, int targetHeight) {
        // Use multi-step scaling for highest quality
        int currentWidth = original.getWidth();
        int currentHeight = original.getHeight();
        
        BufferedImage scaled = original;
        
        // Scale down in steps
        while (currentWidth > targetWidth * 2 || currentHeight > targetHeight * 2) {
            currentWidth /= 2;
            currentHeight /= 2;
            if (currentWidth < targetWidth) currentWidth = targetWidth;
            if (currentHeight < targetHeight) currentHeight = targetHeight;
            
            BufferedImage temp = new BufferedImage(currentWidth, currentHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = temp.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(scaled, 0, 0, currentWidth, currentHeight, null);
            g2d.dispose();
            scaled = temp;
        }
        
        // Final scaling to exact target size
        BufferedImage finalImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = finalImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(scaled, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        
        return finalImage;
    }

    private JPanel createPhotoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 248, 248));
        panel.setPreferredSize(new Dimension(250, 300));
        
        try {
            File imageFile = new File(IMAGE_PATH);
            if (!imageFile.exists()) {
                throw new Exception("Image file not found");
            }
            
            // Load original image
            BufferedImage originalImage = ImageIO.read(imageFile);
            
            // Calculate aspect ratio preserving dimensions
            double aspectRatio = (double)originalImage.getWidth() / originalImage.getHeight();
            int targetWidth = 220;
            int targetHeight = 280;
            
            if (aspectRatio > 1) {
                // Landscape orientation
                targetHeight = (int)(targetWidth / aspectRatio);
            } else {
                // Portrait or square
                targetWidth = (int)(targetHeight * aspectRatio);
            }
            
            // Get high quality scaled image
            BufferedImage highQualityImage = getHighQualityScaledImage(originalImage, targetWidth, targetHeight);
            
            // Create image container with proper centering
            JPanel imageContainer = new JPanel(new GridBagLayout());
            imageContainer.setBackground(new Color(248, 248, 248));
            
            JLabel photoLabel = new JLabel(new ImageIcon(highQualityImage));
            photoLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            
            imageContainer.add(photoLabel);
            panel.add(imageContainer, BorderLayout.CENTER);
            
        } catch (Exception e) {
            // Fallback with error message
            JLabel placeholder = new JLabel("Profile Photo");
            placeholder.setFont(new Font("Arial", Font.PLAIN, 16));
            placeholder.setPreferredSize(new Dimension(220, 280));
            placeholder.setHorizontalAlignment(SwingConstants.CENTER);
            placeholder.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
            panel.add(placeholder, BorderLayout.CENTER);
            JOptionPane.showMessageDialog(this, 
                "Image Error: " + e.getMessage() + "\nPath: " + IMAGE_PATH, 
                "Image Loading Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        return panel;
    }

    private JPanel createContentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230)), 
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        contentLabel = new JLabel(CONTENT_SECTIONS[0]);
        contentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        panel.add(contentLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));
        
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        
        // Style buttons with light colors
        for (JButton button : new JButton[]{prevButton, nextButton}) {
            button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            button.setBackground(new Color(240, 240, 240));
            button.setForeground(new Color(80, 80, 80));
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
            button.setFocusPainted(false);
        }
        
        // Add button functionality
        prevButton.addActionListener(e -> showPreviousContent());
        nextButton.addActionListener(e -> showNextContent());
        
        panel.add(prevButton);
        panel.add(nextButton);
        
        return panel;
    }

    private void startContentRotation() {
        Timer timer = new Timer(5000, e -> showNextContent());
        timer.start();
    }

    private void showNextContent() {
        currentIndex = (currentIndex + 1) % CONTENT_SECTIONS.length;
        contentLabel.setText(CONTENT_SECTIONS[currentIndex]);
    }

    private void showPreviousContent() {
        currentIndex = (currentIndex - 1 + CONTENT_SECTIONS.length) % CONTENT_SECTIONS.length;
        contentLabel.setText(CONTENT_SECTIONS[currentIndex]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new AnkitaProfile();
        });
    }
}