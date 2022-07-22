import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StikersGenerator {

    public void create(InputStream inputStream, String fileName) throws IOException {

        // Ler imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Criar nova imagem com novo tamanho e com fundo transparente
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;

        var newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // Copiar imagem original para a nova imagem
        Graphics2D graphics2D = (Graphics2D) newImage.getGraphics();
        graphics2D.drawImage(originalImage, 0, 0, null);

        // Personalizar nova imagem
        // Configurar fonte
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(font);

        // Escrever frase na nova imagem
        graphics2D.drawString("Topzera", 100, newHeight - 100);

        // Salvar nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File("saida/" + fileName + ".png"));
    }
}
