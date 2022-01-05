package goSecuri;
import java.util.*;
import java.io.*;


public class Test {
	private static ListIterator<String> li;
	public static void main(String args[]) {
		genererFile("https://raw.githubusercontent.com/dremaux/projetMSPR/main/staff.txt", "staff.txt");
		genererFile("https://raw.githubusercontent.com/dremaux/projetMSPR/main/liste.txt", "liste.txt");
		genererIndex();
		genererFiles();

	}

	public static void genererFiles() {
		ArrayList<String> list = genererCollection();
		li = list.listIterator();

		for(int i = 0;i < list.size();i++){
			new Thread() {
				public void run() {
					String name = li.next();
					genererFile("https://raw.githubusercontent.com/dremaux/projetMSPR/main/" + name + ".txt", name + ".txt");
					genererAgent(name);

				}
			}.start();
		}
	}

	public static void genererFile(String url, String name) {
		Okhttp ok = new Okhttp();
		try {
			String reponse = ok.run(url);
			PrintWriter writer = new PrintWriter("C://ProjEpsi/"+name, "UTF-8");
			writer.print(reponse);
			writer.close();
		}
		catch(IOException e) {

		}
	}

	public static ArrayList<String> genererCollection(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("C://ProjEpsi/staff.txt"));
			try {
				String line = br.readLine();

				while (line != null) {
					list.add(line);
					line = br.readLine();
				}
			} 
			finally {
				br.close();
			}
		}
		catch(IOException e) {

		}
		return list;
	}

	public static void genererAgent(String name) {
		boolean find = false;
		try {
			BufferedReader brl = new BufferedReader(new FileReader("C://ProjEpsi/liste.txt"));
			BufferedReader br = new BufferedReader(new FileReader("C://ProjEpsi/"+ name +".txt"));
			PrintWriter writer = new PrintWriter("C://ProjEpsi/" + name +".html", "UTF-8");
			writer.print("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<FONT face=\"roboto\">\r\n"
					+ "    <head>\r\n"
					+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"
					+ "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\r\n"
					+ "        <meta name=\"description\" content=\"\" />\r\n"
					+ "        <meta name=\"author\" content=\"\" />\r\n"
					+ "        <title>Full Width Pics - Start Bootstrap Template</title>\r\n"
					+ "        <!-- Favicon-->\r\n"
					+ "        <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\r\n"
					+ "        <!-- Core theme CSS (includes Bootstrap)-->\r\n"
					+ "        <link href=\"css/styles.css\" rel=\"stylesheet\" />\r\n"
					+ "    </head>\r\n"
					+ "    <body style=\"Light\">\r\n"
					+ "        <!-- Responsive navbar-->\r\n"
					+ "        <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n"
					+ "            <div class=\"container\">\r\n"
					+ "                <a class=\"navbar-brand\" href=\"index.html\">Go Securi</a>\r\n"
					+ "                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\r\n"
					+ "                \r\n"
					+ "            </div>\r\n"
					+ "        </nav>\r\n"
					+ "        \r\n"
					+ "        <div class=\"name\">\r\n"
					+ "           <p>" + br.readLine() + " " + br.readLine());
			br.readLine();
			br.readLine();
			br.readLine();
			writer.print("</div>\r\n"
					+ "\r\n"
					+ "        <div class=\"photo\">" + "<img src=\"https://raw.githubusercontent.com/dremaux/projetMSPR/main/" + name + ".jpg\"height=\"200\" weith=\"200\">" + "</div>\r\n"
					+ "        <!-- Content section-->\r\n"
					+ "        <section class=\"py-5\">\r\n"
					+ "            <div class=\"container my-5\">\r\n"
					+ "                <div class=\"row justify-content-center\">\r\n"
					+ "                    <div class=\"col-lg-6\">");
			br.close();
			try {
				String linel = brl.readLine();
				while (linel != null) {
					String[] s = linel.split(" ");
					br = new BufferedReader(new FileReader("C://ProjEpsi/"+ name +".txt"));
					String line = br.readLine();
					while(line != null) {
						if(line.compareTo(s[0]) == 0) {
							find = true;
						}
						line = br.readLine();
					}
					br.close();
					if(find) {
						find = false;
						for(int i = 1; i < s.length;i++) {
							writer.print(s[i] + " ");
						}
						writer.print("<br>");
					}
					else {
						writer.print("<del>");
						for(int i = 1; i < s.length;i++) {
							writer.print(s[i] + " ");
						}
						writer.print("</del><br>");
					}
					linel = brl.readLine();
				}
			} 
			finally {
				brl.close();
			}
			writer.print("</div>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </section>\r\n"
					+ "        <!-- Footer-->\r\n"
					+ "        <footer class=\"py-5 bg-dark\">\r\n"
					+ "            <div class=\"container\"><p class=\"m-0 text-center text-white\">Copyright &copy; Go Securi</p></div>\r\n"
					+ "        </footer>\r\n"
					+ "        <!-- Bootstrap core JS-->\r\n"
					+ "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
					+ "        <!-- Core theme JS-->\r\n"
					+ "        <script src=\"js/scripts.js\"></script>\r\n"
					+ "    </body>\r\n"
					+ "</html>");
			
			writer.close();
		}
		catch(IOException e) {

		}
	}

	public static void genererIndex() {
		try {
			PrintWriter writer = new PrintWriter("C://ProjEpsi/index.html", "UTF-8");
			writer.print("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<FONT face=\"roboto\">\r\n"
					+ "    <head>\r\n"
					+ "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />"
					+ "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\r\n"
					+ "        <meta name=\"description\" content=\"\" />\r\n"
					+ "        <meta name=\"author\" content=\"\" />\r\n"
					+ "        <title>Go Securi</title>\r\n"
					+ "        <!-- Favicon-->\r\n"
					+ "        <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\r\n"
					+ "        <!-- Core theme CSS (includes Bootstrap)-->\r\n"
					+ "        <link href=\"css/styles.css\" rel=\"stylesheet\" />\r\n"
					+ "    </head>\r\n"
					+ "    <body style=\"Light\">\r\n"
					+ "        <!-- Responsive navbar-->\r\n"
					+ "        <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n"
					+ "            <div class=\"container\">\r\n"
					+ "                <a class=\"navbar-brand\" href=\"index.html\">Go Securi</a>\r\n"
					+ "                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\r\n"
					+ "                \r\n"
					+ "            </div>\r\n"
					+ "        </nav>\r\n"
					+ "        <!-- Header - set the background image for the header in the line below-->\r\n"
					+ "        <header class=\"py-5 bg-image-full\">\r\n"
					+ "            <div class=\"text-center my-5\">\r\n"
					+ "                <img class=\"img-fluid rounded-circle mb-4\" src=\"assets/logo.png\" />\r\n"
					+ "                <h1 class=\"text-white fs-3 fw-bolder\">GO Securi</h1>\r\n"
					+ "                <p class=\"text-white-50 mb-0\">The best of security</p>\r\n"
					+ "            </div>\r\n"
					+ "        </header>\r\n"
					+ "        \r\n"
					+ "        <!-- Content section-->\r\n"
					+ "        <section class=\"py-5\">\r\n"
					+ "            <div class=\"container my-5\">\r\n"
					+ "                <div class=\"row justify-content-center\">\r\n"
					+ "                    <div class=\"col-lg-6\">");
			BufferedReader br = new BufferedReader(new FileReader("C://ProjEpsi/staff.txt"));
			try {
				String line = br.readLine();

				while (line != null) {
					writer.print("<a href=\"" + line + ".html\">" + line + "</a><br>");
					line = br.readLine();
				}
			} finally {
				br.close();
			}
			writer.print("                    </div>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "        </section>\r\n"
					+ "        <!-- Footer-->\r\n"
					+ "        <footer class=\"py-5 bg-dark\">\r\n"
					+ "            <div class=\"container\"><p class=\"m-0 text-center text-white\">Copyright &copy; Go Securi</p></div>\r\n"
					+ "        </footer>\r\n"
					+ "        <!-- Bootstrap core JS-->\r\n"
					+ "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
					+ "        <!-- Core theme JS-->\r\n"
					+ "        <script src=\"js/scripts.js\"></script>\r\n"
					+ "    </body>\r\n"
					+ "</FONT>\r\n"
					+ "</html>");
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
