package com.example;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WCMServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            WCMService wcmService = WCMService.getInstance(req);
            String action = req.getParameter("action");

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            if (action == null || action.equals("selectLibrary")) {
                List<String> libraries = wcmService.getLibraries();
                JSONArray jsonArray = new JSONArray(libraries);
                resp.getWriter().write(jsonArray.toString());

            } else if (action.equals("selectDocumentType")) {
                String selectedLibrary = req.getParameter("library");
                wcmService.setCurrentLibrary(selectedLibrary);

                List<String> documentTypes = wcmService.getDocumentTypes();
                JSONArray jsonArray = new JSONArray(documentTypes);
                resp.getWriter().write(jsonArray.toString());

            } else if (action.equals("selectItems")) {
                String documentType = req.getParameter("documentType");
                List<String> items = wcmService.getItemsByDocumentType(documentType);
                JSONArray jsonArray = new JSONArray(items);
                resp.getWriter().write(jsonArray.toString());

            } else if (action.equals("viewItemMetadata")) {
                String itemName = req.getParameter("itemName");
                String documentType = req.getParameter("documentType");
                Map<String, String> metadata = wcmService.getItemMetadata(itemName, documentType);
                JSONObject jsonObject = new JSONObject(metadata);
                resp.getWriter().write(jsonObject.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Error processing request\"}");
        }
    }
}
