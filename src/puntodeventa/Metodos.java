/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntodeventa;
import clases.conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Metodos {
    //private final String SQL_INSERT="INSERT INTO registro(usuario, contraseña)values(?, ?)";
    private final String SQL_INSERT_ADMIN = "INSERT INTO registrousers (usuario, contraseña, tipoUser) values (?, ?, ?)";
    private final String SQL_INSERT_PRODUCTOS = "INSERT INTO productos (nombre, costo, cantidad, descripcion, codigo_producto) values (?, ?, ?, ?, ?)";
    private final String SQL_SELECT_TIPOUSER="SELECT tipoUser FROM registrousers WHERE usuario= ?";
    private final String SQL_SELECT_ADMIN = "SELECT contraseña, tipoUser FROM registrousers WHERE usuario=?";
    private final String SQL_DELETE_USUARIOS = "DELETE FROM `registrousers` WHERE id_admin=?";
    private final String SQL_UPDATE = "UPDATE registrousers SET usuario= ?, contraseña= ?, tipoUser= ? WHERE id_admin=?";
    private final String SQL_UPDATE_PASS = "UPDATE registrousers SET contraseña = ? WHERE usuario=?";
    private final String SQL_UPDATE_PRODUCTS = "UPDATE productos SET nombre = ?, costo = ?, cantidad = ?, descripcion = ? WHERE codigo_producto=?";
    private ResultSet RS;
    private PreparedStatement PS;
    private final conectar CONEC;
    
    public Metodos(){
        PS=null;
        CONEC=new conectar();
    }
    
    public int insertProductos(String nombre, String costo, String cantidad, String descripcion, String codigo_producto){
        try{
            PS=CONEC.getConnection().prepareStatement(SQL_INSERT_PRODUCTOS);
            PS.setString(1, nombre);
            PS.setString(2, costo);
            PS.setString(3, cantidad);
            PS.setString(4, descripcion);
            PS.setString(5, codigo_producto);
            
            int res=PS.executeUpdate();
            if(res>0){
                JOptionPane.showMessageDialog(null,"Registro Exitoso","Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        finally{
            PS=null;
        }
        return 0;
    }
    
        public int inserDatosAdmin (String usuario, String contraseña, boolean tipoUser){
        try{
            PS=CONEC.getConnection().prepareStatement(SQL_INSERT_ADMIN);
            PS.setString(1, usuario);
            PS.setString(2, contraseña);
            if(tipoUser == true){
                PS.setString(3, "admin");
            }
            else{
                PS.setString(3, "usuario");
            }
            
            int res=PS.executeUpdate();
            if(res>0){
                JOptionPane.showMessageDialog(null,"Registro Exitoso","Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        finally{
            PS=null;
        }
        return 0;
    }
       
    public void deleteDatos(String value){
      try{
            PS=CONEC.getConnection().prepareStatement(SQL_DELETE_USUARIOS);
            PS.setString(1, value);
            int res=PS.executeUpdate();
            if(res>0){
                JOptionPane.showMessageDialog(null,"Usuario eliminado","Exito",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        finally{
            PS=null;
        }  
    }
   
    public void upDatos(String usuario, String contraseña, boolean tipoAdmin, String id_admin ){
        try{
           PS=CONEC.getConnection().prepareStatement(SQL_UPDATE);
           PS.setString(1, usuario);
           PS.setString(2, contraseña);
           if(tipoAdmin == true){
                PS.setString(3, "admin");
            }
            else{
                PS.setString(3, "usuario");
            }
           PS.setString(4, id_admin);
           
           int res=PS.executeUpdate();
           if(res>0){
               JOptionPane.showMessageDialog(null, "Modificación realizada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
           }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            PS=null;
        }  
    }
    
    public int upDatosPass (String password, String usuario){
        try{
           PS=CONEC.getConnection().prepareStatement(SQL_UPDATE_PASS);
           PS.setString(1, password);
           PS.setString(2,usuario);
           
           int res=PS.executeUpdate();
           if(res>0){
               JOptionPane.showMessageDialog(null, "Contraseña cambiada con éxito", "Exito", JOptionPane.INFORMATION_MESSAGE);
           }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    
     public int updateProductos (String nombre, String costo, String cantidad, String descripcion, String codigo_producto){
        try{
           PS=CONEC.getConnection().prepareStatement(SQL_UPDATE_PRODUCTS);
           PS.setString(5,codigo_producto);
           PS.setString(1, nombre);
           PS.setString(2, costo);
           PS.setString(3, cantidad);
           PS.setString(4, descripcion);
           //PS.setString(5, codigo_producto);
           
           int res=PS.executeUpdate();
           if(res>0){
               JOptionPane.showMessageDialog(null, "Modificacion Cargada Con Exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
           }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    
    /*public int SelectDatos(String usuario,String contra){
        String Con = "";
        int control = 0;
        try{
            PS = CONEC.getConnection().prepareStatement(SQL_SELECT);
            PS.setString(1, usuario);
            RS = PS.executeQuery();
            if(RS.next()){
               Con = RS.getString(1);
            }
            
            if(Con.equals(contra)){
                JOptionPane.showMessageDialog(null, "Excelente","Exito", JOptionPane.INFORMATION_MESSAGE);
               control = 1; 
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        if(control ==1){
            return 1;
        }else{
           return 0;
        }
    }*/
    
    /* public int SelectProductosID (Integer id_producto){
        String Con = "";
        int control = 0;
        try{
            PS = CONEC.getConnection().prepareStatement(SQL_SELECT_PRODUCTOS);
            PS.setInt(1, id_producto);
            RS = PS.executeQuery();
            if(RS.next()){
               Con = RS.getString(1);
            }
            
            if(Con.equals(id_producto)){
                JOptionPane.showMessageDialog(null, "Excelente","Exito", JOptionPane.INFORMATION_MESSAGE);
               control = 1; 
            }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(control ==1){
            return 1;
        } else{
           return 0;
        }
    }*/
     
     
    
    public int SelectDatosAdmin (String usuario,String contra){
        String Con = "";
        int control = 0;
        try{
            PS = CONEC.getConnection().prepareStatement(SQL_SELECT_ADMIN);
            PS.setString(1, usuario);
            RS = PS.executeQuery();
            if(RS.next()){
               Con = RS.getString(1);
            }
            
            if(Con.equals(contra)){
               //JOptionPane.showMessageDialog(null, "Excelente","Exito", JOptionPane.INFORMATION_MESSAGE);
               control = 1; 
            }
            else{
                control =0 ;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return control;
    }
    
    public int SelectTipoUser (String usuario){
        String Con = "";
        int tipo = 0;
        try{
            PS = CONEC.getConnection().prepareStatement(SQL_SELECT_TIPOUSER);
            PS.setString(1, usuario);
            RS = PS.executeQuery();
            if(RS.next()){
               Con = RS.getString(1);
            }
            
            if(Con.equals("admin")){
               tipo = 1;
            }
            else{
               tipo = 0;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return tipo;
    }
    
    public boolean confirmacionSalida(){
        int reply = JOptionPane.showConfirmDialog(null, "¿Realmente quieres salir?", "Atención!", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
          return true;
        }
        else {
           return false;
        }
    }
    
    
    
}
