package DAO;

import DTO.EventoDTO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rezen
 */
public class EventoDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<EventoDTO> lista = new ArrayList<>();
     

    public void cadastrarEvento(EventoDTO objEventoDTO) {
        String sql = "insert into evento (dia_evento,mes_evento,nome_evento,tipo_evento,cliente,local_evento) values (?,?,?,?,?,?)";

        conn = new ConexaoDAO().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, String.valueOf(objEventoDTO.getDiaEvento()));
            pstm.setString(2, String.valueOf(objEventoDTO.getMesEvento()));
            pstm.setString(3, objEventoDTO.getNomeEvento());
            pstm.setString(4, objEventoDTO.getTipoEvento());
            pstm.setString(5, objEventoDTO.getCliente());
            pstm.setString(6, objEventoDTO.getLocalEvento());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro + "eventoDAO cadastrar");
        }
    }

    public ArrayList<EventoDTO> listarEvento() {
        String sql = "select * from evento";
        conn = new ConexaoDAO().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                EventoDTO objEventoDTO = new EventoDTO();
                objEventoDTO.setID(rs.getInt("ID"));
                objEventoDTO.setDiaEvento(rs.getInt("dia_evento"));
                objEventoDTO.setMesEvento(rs.getInt("mes_evento"));
                objEventoDTO.setNomeEvento(rs.getString("nome_evento"));
                objEventoDTO.setTipoEvento(rs.getString("tipo_evento"));
                objEventoDTO.setCliente(rs.getString("cliente"));
                objEventoDTO.setLocalEvento(rs.getString("local_evento"));

                lista.add(objEventoDTO);

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro + " funcionarioDAO pesquisar");
        }
        return lista;
    }
    

}


