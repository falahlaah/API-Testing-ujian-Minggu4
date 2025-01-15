package com.myCompany;

import com.myCompany.fakeStoreAPI.ChartService;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTestFakestore {
    ChartService chartService;

    // Method ini akan dijalankan sebelum semua pengujian dimulai untuk menginisialisasi objek ChartService
    @BeforeTest
    public void setup() {
        chartService = new ChartService();
    }

    // Method ini dijalankan setelah setiap pengujian untuk mencetak border di konsol
    @AfterMethod
    public void printborderTest(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // Menguji API untuk mendapatkan semua cart
    @Test
    public void getAllCartTest() {
        // Memanggil API untuk mendapatkan semua cart
        ChartService response = chartService.setAllCart();
        String responseBody = response.getResponseBody().asString();
        System.out.println(responseBody);

        // Memastikan bahwa response body mengandung "20"
        Assert.assertEquals(responseBody.contains("20"), true);

        // Memastikan status line dari response adalah HTTP 200 OK
        String ExpectedStatuscode = "HTTP/1.1 200 OK";
        Assert.assertEquals(chartService.getResponseStatusline(), ExpectedStatuscode);
    }

    // Menguji API untuk mendapatkan cart berdasarkan ID tertentu
    @Test
    public void singleCartTest() {
        // Memanggil API untuk mendapatkan satu cart berdasarkan ID
        ChartService response = chartService.setSingleCart("5");
        String responseBody = response.getResponseBody().asString();
        System.out.println(responseBody);

        // Memeriksa apakah ID dalam response JSON sama dengan ID yang diminta
        String responseJson = response.asJSON().getString("id");
        String ExpectedJason = "5";
        Assert.assertEquals(responseJson,ExpectedJason);

        // Memastikan status line dari response adalah HTTP 200 OK
        String ExpectedStatuscode = "HTTP/1.1 200 OK";
        Assert.assertEquals(chartService.getResponseStatusline(), ExpectedStatuscode);
    }

    // Menguji API untuk mendapatkan cart dengan parameter limit
    @Test
    public void limitCartTest() {
        // Memanggil API dengan parameter query "limit"
        ChartService response = chartService.setQueryParams("limit","5");
        String responseBody = response.getResponseBody().asString();
        System.out.println(responseBody);

        // Memastikan status line dari response adalah HTTP 200 OK
        String ExpectedStatuscode = "HTTP/1.1 200 OK";
        Assert.assertEquals(chartService.getResponseStatusline(), ExpectedStatuscode);
    }

    // Menguji API untuk mendapatkan cart dengan parameter sorting
    @Test
    public void sortCartTest() {
        // Memanggil API dengan parameter query "order"
        ChartService response = chartService.setQueryParams("order","desc");
        String responseBody = response.getResponseBody().asString();
        System.out.println(responseBody);

        // Memastikan status line dari response adalah HTTP 200 OK
        String ExpectedStatuscode = "HTTP/1.1 200 OK";
        Assert.assertEquals(chartService.getResponseStatusline(), ExpectedStatuscode);
    }

    // Menguji API untuk mendapatkan cart dalam rentang tanggal tertentu
    @Test
    public void inaDateRangeCartTest() {
        // Memanggil API dengan parameter query untuk rentang tanggal
        ChartService response = chartService.setQueryParams("startdate","2019-12-10","enddate","2020-10-10");
        String responseBody = response.getResponseBody().asString();
        System.out.println(responseBody);

        // Memastikan status line dari response adalah HTTP 200 OK
        String ExpectedStatuscode = "HTTP/1.1 200 OK";
        Assert.assertEquals(chartService.getResponseStatusline(), ExpectedStatuscode);
    }

    // Menguji API untuk mendapatkan cart berdasarkan user tertentu
    @Test
    public void UserCartTest() {
        // Memanggil API untuk mendapatkan cart berdasarkan ID user
        ChartService response = chartService.setCartBaseOnUser("2");
        String responseBody = response.getResponseBody().asString();
        System.out.println(responseBody);

        // Memeriksa ID pada cart pertama dalam response JSON
        String responseJson = response.asJSON().getString("[0].id");
        String ExpectedJason = "3";
        Assert.assertEquals(responseJson,ExpectedJason);

        // Memastikan status line dari response adalah HTTP 200 OK
        String ExpectedStatuscode = "HTTP/1.1 200 OK";
        Assert.assertEquals(chartService.getResponseStatusline(), ExpectedStatuscode);
    }

    @Test()
    public void createCartTest() {
        // Membuat objek JSON untuk merepresentasikan body request
        JSONObject body = new JSONObject();

        // Menambahkan atribut ke dalam body request
        body.put("id", "11");
        body.put("userId", "99");
        body.put("date", "2020-02-03.");
        body.put("products", "[{productId:88,quantity:20},{productId:89,quantity:200}]");

        // Mengirim request untuk membuat cart baru dengan body JSON yang telah dibuat
        String hasil = chartService.createCart(body).getResponseBody().asString();
        System.out.println(hasil);

        // Memastikan status line dari response adalah HTTP 200 OK
        String ExpectedStatuscode = "HTTP/1.1 200 OK";
        Assert.assertEquals(chartService.getResponseStatusline(), ExpectedStatuscode);
    }




}
