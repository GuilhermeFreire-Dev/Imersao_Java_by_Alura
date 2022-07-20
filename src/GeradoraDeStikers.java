import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GeradoraDeStikers {

    public void criar(InputStream inputStream, String nomeDoArquivo) throws IOException {

        // Ler imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // Criar nova imagem com novo tamanho e com fundo transparente
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TRANSLUCENT);

        // Copiar imagem original para a nova imagem
        Graphics2D graphics2D = (Graphics2D) novaImagem.getGraphics();
        graphics2D.drawImage(imagemOriginal, 0, 0, null);

        // Personalizar nova imagem
        // Configurar fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(fonte);

        // Escrever frase na nova imagem
        graphics2D.drawString("Topzera", 100, novaAltura - 100);

        // Salvar nova imagem em um arquivo
        File img = new File("saida/" + nomeDoArquivo);

        if (img.mkdirs()){
            ImageIO.write(novaImagem, "png", img);
        }
    }
}
