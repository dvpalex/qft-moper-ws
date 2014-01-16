package br.com.ninb.moper.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FacesUtil implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7608170332858484364L;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dateFormatHora = new SimpleDateFormat("hh:mm:ss");
    private static final int TAMANHO_BUFFER = 2048; // 2 Kb
    private HashMap<String, Locale> locales = null;
    private static int selectedIndex=0;

    public FacesUtil() {
        locales = new HashMap<String, Locale>(4);
        locales.put("BR", new Locale("br", "BR"));
        locales.put("EUA", new Locale("en", "EN"));
    }

    public static FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    public static String getCaminho(String arquivo) {
        return getServletContext().getRealPath(arquivo);
    }

    public static ExternalContext getExternalContext() {
        return getContext().getExternalContext();
    }

    public static void redirect(Object obj) {
        try {
            getContext().getExternalContext().redirect(String.valueOf(obj));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ServletContext getServletContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    public HttpServletRequest getHttpRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public Cookie[] getCookies() {
        return getHttpRequest().getCookies();
    }

    @SuppressWarnings("rawtypes")
    public Map getMap() {
        return getExternalContext().getRequestParameterMap();
    }

    public String getRealPath(String arquivo) {
        return getServletContext().getRealPath(arquivo);
    }

    public String getPaginaAtual() {
        return getContext().getViewRoot().getViewId();
    }

    public String getParammeter(String nome) {
        return (String) getMap().get(nome);
    }

    public Integer getParammeterASInteger(String nome) {
        return Integer.parseInt(String.valueOf(getMap().get(nome)));
    }

    public static HttpSession getSession() {
        return (HttpSession) getExternalContext().getSession(true);
    }

    public Map<String, Object> getSessionMap() {
        return getExternalContext().getSessionMap();
    }

    public static void setAttribute(String name, Object obj) {
        getSession().setAttribute(name, obj);

    }

    public static Object getAttribute(String name) {
        return getSession().getAttribute(name);
    }

    public void sessionClose() {
        getSessionMap().clear();
        getSession().invalidate();
    }

    public static String md5(String senha) {
        String novaSenha = "";

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            novaSenha = hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return novaSenha;
    }

    public static String getIp() {

        return getRequest().getRemoteAddr();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public Map<String, String> getRequestHeaderMap() {
        return getExternalContext().getRequestHeaderMap();
    }

    public String getNavegadorUsuario() {
        return getRequestHeaderMap().get("user-agent");
    }

    public static HttpServletResponse getResponse(){
        return (HttpServletResponse)getExternalContext().getResponse();
    }


    public static void addCookie(Cookie cookie){
        getResponse().addCookie(cookie);
    }

    // public void addJavascriptCall(String call) {
    // JavascriptContext.addJavascriptCall(getContext(), call);
    // }
    //
    // public void addJavascriptCallOpen(String page) {
    // JavascriptContext
    // .addJavascriptCall(
    // getContext(),
    // "window.open(\""
    // + page
    // + "\", \"JANELA\", \"height = 600, width = 700, scrollbars=true\");");
    // }

    public String URLEncoder(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String URLDecoder(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getArquivoParaString(String file) {
        String s = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(
                    getRealPath(file)));
            while (bf.ready()) {
                s += bf.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String getArquivoParaStringUpload(String file) {
        String s = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(
                    new File(file)));
            while (bf.ready()) {
                s += bf.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Calcula a diferença de duas datas em dias <br>
     * <b>Importante:</b> Quando realiza a diferença em dias entre duas datas,
     * este método considera as horas restantes e as converte em fração de dias.
     * 
     * @param dataInicial
     * @param dataFinal
     * @return quantidade de dias existentes entre a dataInicial e dataFinal.
     */
    public static double diferencaEmDias(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24; // resultado
                                                                    // é
                                                                    // diferença
                                                                    // entre as
                                                                    // datas em
                                                                    // dias
        long horasRestantes = (diferenca / 1000) / 60 / 60 % 24; // calcula as
                                                                    // horas
                                                                    // restantes
        result = diferencaEmDias + (horasRestantes / 24d); // transforma as
                                                            // horas restantes
                                                            // em fração de
                                                            // dias

        return result;
    }

    /**
     * Calcula a diferença de duas datas em horas <br>
     * <b>Importante:</b> Quando realiza a diferença em horas entre duas datas,
     * este método considera os minutos restantes e os converte em fração de
     * horas.
     * 
     * @param dataInicial
     * @param dataFinal
     * @return quantidade de horas existentes entre a dataInicial e dataFinal.
     */
    public static double diferencaEmHoras(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        long diferencaEmHoras = (diferenca / 1000) / 60 / 60;
        long minutosRestantes = (diferenca / 1000) / 60 % 60;
        double horasRestantes = minutosRestantes / 60d;
        result = diferencaEmHoras + (horasRestantes);

        return result;
    }

    /**
     * Calcula a diferença de duas datas em minutos <br>
     * <b>Importante:</b> Quando realiza a diferença em minutos entre duas
     * datas, este método considera os segundos restantes e os converte em
     * fração de minutos.
     * 
     * @param dataInicial
     * @param dataFinal
     * @return quantidade de minutos existentes entre a dataInicial e dataFinal.
     */
    public static double diferencaEmMinutos(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmMinutos = (diferenca / 1000) / 60; // resultado é
                                                                // diferença
                                                                // entre as
                                                                // datas em
                                                                // minutos
        long segundosRestantes = (diferenca / 1000) % 60; // calcula os segundos
                                                            // restantes
        result = diferencaEmMinutos + (segundosRestantes / 60d); // transforma
                                                                    // os
                                                                    // segundos
                                                                    // restantes
                                                                    // em
                                                                    // minutos

        return result;
    }

    public String formataData(Date date, String pattern) {
        if (pattern != null) {
            dateFormat = new SimpleDateFormat(pattern);
        }
        return dateFormat.format(date);
    }

    public String formataHora(Date date, String pattern) {
        if (pattern != null) {
            dateFormatHora = new SimpleDateFormat(pattern);
        }
        return dateFormatHora.format(date);
    }

    public static Image redimensiona(String caminho, int w, int h,
            String nometumb) {
        BufferedImage fundo = null;
        try {
            fundo = ImageIO.read(new File(caminho));

            BufferedImage novaImagem = new BufferedImage(w, h,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = novaImagem.createGraphics();
            g.drawImage(fundo, 0, 0, w, h, null);

            ImageIO.write(novaImagem, "PNG", new File(nometumb));
        }

        catch (IOException e1) {
            fundo = new BufferedImage(1, 1, BufferedImage.BITMASK);
        }

        return fundo.getScaledInstance(w, h, 10000);
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    public void copyDirectory(File sourceDir, File destDir) throws IOException {

        if (!destDir.exists()) {

            destDir.mkdir();

        }

        File[] children = sourceDir.listFiles();

        for (File sourceChild : children) {

            String name = sourceChild.getName();

            File destChild = new File(destDir, name);

            if (sourceChild.isDirectory()) {

                copyDirectory(sourceChild, destChild);

            }

            else {

                copyFile(sourceChild, destChild);

            }

        }

        delete(sourceDir);

    }

    public void criaPasta(File source) {
        if (!source.exists()) {

            source.mkdir();

        }
    }

    public void copyFile(File source, File dest) throws IOException {

        if (!dest.exists()) {

            dest.createNewFile();

        }

        InputStream in = null;

        OutputStream out = null;

        try {

            in = new FileInputStream(source);

            out = new FileOutputStream(dest);

            byte[] buf = new byte[1024];

            int len;

            while ((len = in.read(buf)) > 0) {

                out.write(buf, 0, len);

            }

        }

        finally {

            in.close();

            out.close();

        }

    }

    public boolean delete(File resource) throws IOException {

        if (resource.isDirectory()) {

            File[] childFiles = resource.listFiles();

            for (File child : childFiles) {

                delete(child);

            }

        }

        return resource.delete();

    }

    public String carregar(File file) throws FileNotFoundException, IOException {

        if (!file.exists()) {
            return null;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuffer bufSaida = new StringBuffer();

        String linha;
        while ((linha = br.readLine()) != null) {
            bufSaida.append(linha + "\n");
        }
        br.close();
        return bufSaida.toString();
    }

    public File criaArquivo(byte[] bytes, File dest) {
        if(dest.exists()){
            try {
                delete(dest);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        if (!dest.exists()) {

            try {
                dest.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(dest);
            fos.write(bytes);
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return dest;
    }

    /**
     * Coloca as primeiras letras de uma frase em maiusculo
     * 
     * @param palavra
     * @return palara alterada
     */
    public static String fcase(String palavra) {
        if (palavra != null) {
            int len = palavra.length();
            String out = "";
            out += palavra.substring(0, 1).toUpperCase();
            out += palavra.substring(1, len).toLowerCase();
            return out;
        }
        return palavra;
    }

    public String formataDecimal(float precoPromocao) {
        return new DecimalFormat("0.00").format(precoPromocao)
                .replace('.', ',');
    }

    public String extrairZip(File arquivoZip, File diretorio)
            throws ZipException, IOException {
        if (!diretorio.exists()) {
            diretorio.delete();
        }

        ZipFile zip = null;
        File arquivo = null;
        InputStream is = null;
        OutputStream os = null;
        byte[] buffer = new byte[TAMANHO_BUFFER];
        try {
            // cria diretÃ³rio informado, caso nÃ£o exista
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }
            if (!diretorio.exists() || !diretorio.isDirectory()) {
                throw new IOException("Informe um diretÃ³rio vÃ¡lido");
            }
            zip = new ZipFile(arquivoZip);
            Enumeration<?> e = zip.entries();
            while (e.hasMoreElements()) {
                ZipEntry entrada = (ZipEntry) e.nextElement();
                arquivo = new File(diretorio, entrada.getName());
                // se for diretÃ³rio inexistente, cria a estrutura
                // e pula pra prÃ³xima entrada
                if (entrada.isDirectory() && !arquivo.exists()) {
                    arquivo.mkdirs();
                    continue;
                }
                // se a estrutura de diretÃ³rios nÃ£o existe, cria
                if (!arquivo.getParentFile().exists()) {
                    arquivo.getParentFile().mkdirs();
                }
                try {
                    // lÃª o arquivo do zip e grava em disco
                    is = zip.getInputStream(entrada);
                    os = new FileOutputStream(arquivo);
                    int bytesLidos = 0;
                    if (is == null) {
                        throw new ZipException("Erro ao ler a entrada do zip: "
                                + entrada.getName());
                    }
                    while ((bytesLidos = is.read(buffer)) > 0) {
                        os.write(buffer, 0, bytesLidos);
                    }
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception ex) {
                        }
                    }
                    if (os != null) {
                        try {
                            os.close();
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (Exception e) {
                }
            }
        }
        return zip.getName();
    }

    public void chooseLocaleFromLink(String current) {
        getContext().getViewRoot().setLocale((Locale) locales.get(current));
    }

    public static int getSelectedIndex() {
        return selectedIndex;
    }

    public static void setSelectedIndex(int selectedIndex) {
        FacesUtil.selectedIndex = selectedIndex;
    }
}