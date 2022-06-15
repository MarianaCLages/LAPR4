package eapli.base.surveymanagement.application;

public class GenerateHTMLReportService {

    public String generateHtmlReport() {

        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append("<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>AGV Status Dashboard</title>\n" +
                "    <link rel=\"icon\" type=\"image/x-icon\" href=\"amazon2.png\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <script src=\"rcomp-ajax.js\"></script>\n" +
                "    <style>\n" +
                "\n" +
                "        .agv {\n" +
                "            border-radius: 20px;\n" +
                "            style: padding 10px;\n" +
                "            border: 2px black;\n" +
                "            background-color: white;\n" +
                "        }\n" +
                "\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        html, body {\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "\n" +
                "        header {\n" +
                "            height: 25%;\n" +
                "        }\n" +
                "\n" +
                "        section {\n" +
                "            height: 65%;\n" +
                "        }\n" +
                "\n" +
                "        footer {\n" +
                "            height: 10%;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            text-align: center;\n" +
                "            color: white;\n" +
                "            text-transform: uppercase;\n" +
                "            padding: 1px;\n" +
                "            font-weight: 100;\n" +
                "            position: relative;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        h1 a {\n" +
                "            background: black;\n" +
                "            display: block;\n" +
                "            padding: 20px;\n" +
                "            text-decoration: none;\n" +
                "            letter-spacing: 20px;\n" +
                "            color: white;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "\n" +
                "            clear: left;\n" +
                "            display: block;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        .context h1 {\n" +
                "            text-align: center;\n" +
                "            color: #fff;\n" +
                "            font-size: 30px;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        @keyframes animate {\n" +
                "\n" +
                "            50% {\n" +
                "                transform: translateY(0) rotate(0deg);\n" +
                "                opacity: 1;\n" +
                "                border-radius: 0;\n" +
                "            }\n" +
                "\n" +
                "            50% {\n" +
                "                transform: translateY(-1000px) rotate(720deg);\n" +
                "                opacity: 0;\n" +
                "                border-radius: 50%;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        footer {\n" +
                "            position: static;\n" +
                "            text-align: center;\n" +
                "            bottom: 0px;\n" +
                "            width: 100%;\n" +
                "            font-size: medium;\n" +
                "            style: padding 10px;\n" +
                "            border: 2px black;\n" +
                "            background-color: aliceblue;\n" +
                "            font-family: 'Slabo 27px', serif;\n" +
                "        }\n" +
                "\n" +
                "        footer address {\n" +
                "            font-size: 1em;\n");

        stringBuilder.append(".Frame {\n" +
                "             display: table;\n" +
                "             height: 100%;\n" +
                "             width: 100%;\n" +
                "        }\n" +
                "        .Row {\n" +
                "             display: table-row;\n" +
                "             height: 1px;\n" +
                "        }\n" +
                "        .Row.Expand {\n" +
                "             height: auto;\n" +
                "        }");


        stringBuilder.append("        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body bgcolor=\"#00a2e8\" onload=\"refreshStatus();\">\n" +
                "\n" +
                "<center>\n" +
                "    <h1>\n" +
                "        <a href=\"#0\">\n" +
                "            Statistical Report\n" +
                "        </a>\n" +
                "    </h1>\n" +
                "\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "\n" +
                "\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "\n" +
                "    <div class=\"agv\" id=\"agv\">\n<p>");

        return stringBuilder.toString();

    }

    public String generateEndPageHtmlReport() {

        StringBuilder sc = new StringBuilder();

        sc.append("</p></div>\n" +
                "\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "\n" +
                "    <section class=\"Row\"><h3>LEI-2DJ-G01</h3></section> \n" +
                "    <footer class=\"Row Expand\">" +
                " <table style=\"width:115%\">\n" +
                "            <tr>\n" +
                "                <td>Miguel Jordao</td>\n" +
                "                <td>1201487@isep.ipp.pt</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Mariana Lages</td>\n" +
                "                <td>1200902@isep.ipp.pt</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Tiago Ferreira</td>\n" +
                "                <td>1200601@isep.ipp.pt</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Eduardo Sousa</td>\n" +
                "                <td>1200920@isep.ipp.pt</td>\n" +
                "            </tr>\n" +
                "        </table></footer>" +
                "\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>");

        return sc.toString();

    }

}