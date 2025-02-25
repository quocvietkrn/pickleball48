/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import dao.RegisteredPickleBallFieldDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Bill;
import model.RegisteredPickleBallField;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "PaymentBill", urlPatterns = {"/PaymentBill"})
public class PaymentBill extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ThanhtoanBillServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ThanhtoanBillServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RegisteredPickleBallFieldDAO rffDAO = new RegisteredPickleBallFieldDAO();
//        StudyScheduleDAO sSDAO = new StudyScheduleDAO();
        
        int idDonthanhtoan = Integer.parseInt(request.getParameter("idDonthanhtoan"));
        String typeDonthanhtoan = request.getParameter("typeDonthanhtoan");
        //rFF: RegisterFF, sT: scheduleTournament, sS: StudySchedule
        if (typeDonthanhtoan.equals("rFF")) {
            RegisteredPickleBallField donThanhToan = rffDAO.getRegisteredPickleBallFieldByID(idDonthanhtoan);
            request.setAttribute("donThanhToan", donThanhToan);
//            request.setAttribute("typeDonthanhtoan", typeDonthanhtoan);
        }
//        else {
//            StudySchedule donThanhToan = sSDAO.getStudyScheduleByID(idDonthanhtoan);
//            request.setAttribute("donThanhToan", donThanhToan);
//            request.setAttribute("typeDonthanhtoan", typeDonthanhtoan);
//        }
//        ProductDAO productD = new ProductDAO();
//        List<Product> listProduct = new ArrayList<>();
//        try {
//            listProduct = productD.getAllProducts();
//        } catch (SQLException ex) {
//            Logger.getLogger(ThanhtoanBillServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        request.setAttribute("idDonthanhtoan", idDonthanhtoan);
        request.setAttribute("typeDonthanhtoan", typeDonthanhtoan);
//        request.setAttribute("listProduct", listProduct);
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson json = new Gson();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        // Lấy thông tin từ request
        String bookingTime = request.getParameter("bookingTime");
        String bookerName = request.getParameter("bookerName");
        String phone = request.getParameter("phone");
        int idDonthanhtoan = Integer.parseInt(request.getParameter("idDonthanhtoan"));
        String typeDonthanhtoan = request.getParameter("typeDonthanhtoan");
        // Lấy thông tin về các loại nước đã thêm
//        String[] selectedWaterInfo = request.getParameterValues("selectedWaterInfo");
//        List<waterinfo> waterInfoList = new ArrayList<>();
//        if (selectedWaterInfo != null) {
//            // Chuyển chuỗi JSON thành danh sách đối tượng WaterInfo
//            java.lang.reflect.Type listType = new TypeToken<List<waterinfo>>() {
//            }.getType();
//             waterInfoList = json.fromJson(selectedWaterInfo[0], listType);
//            // Lặp qua danh sách và lấy thông tin
//        }
        BillDAO billDAO = new BillDAO();
        RegisteredPickleBallFieldDAO rffDAO = new RegisteredPickleBallFieldDAO();
        
//        StudyScheduleDAO sSDAO = new StudyScheduleDAO();
        Bill bill = new Bill();
        //tong bill
        int toltalPrice =0;
        String invoice = "AA" + idDonthanhtoan + typeDonthanhtoan;
        bill.setInvoice(invoice);
        if (typeDonthanhtoan.equals("rFF")) {
            bill.setRegisteredPickleBallField(rffDAO.getRegisteredPickleBallFieldByID(idDonthanhtoan));
            rffDAO.changeStatusWithIDRegisteredFootballField(3, idDonthanhtoan);
            toltalPrice = rffDAO.getRegisteredPickleBallFieldByID(idDonthanhtoan)
                    .getPickleBallFieldSchedule().getPickleBallField().getPrice() - rffDAO.getRegisteredPickleBallFielByID(idDonthanhtoan)
                    .getDeposit();
            if(rffDAO.getRegisteredPickleBallFielByID(idDonthanhtoan).getRegistFindOppoent()!=null){
                toltalPrice-=100000;
            }
        } else if (typeDonthanhtoan.equals("sT")) {
            bill.setScheduleTournament(sTDAO.getScheduleStounamentFullInfoById(idDonthanhtoan));
            sTDAO.updateStatusScheduleTournament(idDonthanhtoan);
            toltalPrice = sTDAO.getScheduleStounamentFullInfoById(idDonthanhtoan).getFootballFieldSchedule().getFootballField().getPrice();
        }
//        else {
//            bill.setStudySchedule(sSDAO.getStudyScheduleByID(idDonthanhtoan));
//            toltalPrice = sSDAO.getStudyScheduleByID(idDonthanhtoan).getFootballFieldSchedule().getFootballField().getPrice();
//        }
//        bill.setStudySchedule(sSDAO.getStudyScheduleByID(idDonthanhtoan));
        bill.setAccount2(user);
        // Lấy thời gian hiện tại
        Date currentDate = new Date();

        // Định dạng ngày giờ
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Chuyển đối tượng Date thành chuỗi
        String dateString = dateFormat.format(currentDate);
        bill.setPaymentDate(dateString);
        if (typeDonthanhtoan.equals("rFF")) {
            billDAO.insertBillWithRegisteredFootballField(bill);
        } else if (typeDonthanhtoan.equals("sT")) {
            billDAO.insertBillWithScheduleTournament(bill);
        } 
//        else {
//            billDAO.insertBillWithStudySchedule(bill);
//        }
//        ProductDAO productDAO = new ProductDAO();
//        ProductDetailDAO productDetailDAO = new ProductDetailDAO();
        int lastIndexBill = billDAO.getIDBillLastIndex();
        //tong bill
        
//        for (waterinfo object : waterInfoList) {
//            if(object.getSoLuong()>0){
////                ProductDetail productDetail = new ProductDetail();
////                productDetail.setiDProduct(productDAO.getProductByName(object.getLoaiNuoc()).getId());
//                productDetail.setQuantity(object.getSoLuong());
//                productDetail.setiDBill(lastIndexBill);
//                productDetail.setPrice(object.getGiaTien());
//                productDetailDAO.insertProductDetail(productDetail);
//                toltalPrice+=object.getSoLuong()*object.getGiaTien();
//            }
//        }
        billDAO.updateIDAccount2(lastIndexBill, user.getIDAccount());
        billDAO.updateTotalPricewwithidBillAndTotalPrice(lastIndexBill, toltalPrice);
        response.sendRedirect("ManageField");
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
