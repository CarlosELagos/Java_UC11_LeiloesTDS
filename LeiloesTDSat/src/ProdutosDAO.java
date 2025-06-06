
import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO `produtos` (`nome`, `valor`, `status`) VALUES" + "(?,?,?)";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar produto no cadastro" + e.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> ListarProdutos() {

        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<ProdutosDTO> listagem = new ArrayList<>();
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
            return listagem;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        return null;
    }
    
    public void venderProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto vendido!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto!! " + e.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {

        conn = new conectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<ProdutosDTO> listagem = new ArrayList<>();
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
            return listagem;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        return null;
    }
}
