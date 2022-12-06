//MahasiswaController.java
package com.example.sbd_modul3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sbd_modul3.model.Laptop;
import com.example.sbd_modul3.model.Mahasiswa;
import com.example.sbd_modul3.model.Menjual;
import com.example.sbd_modul3.model.Pembeli;
import com.example.sbd_modul3.model.Toko;

@Controller
public class MahasiswaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
        public String login(Model model){
            return "login";
        }
    
    @GetMapping("/index")
        public String index(Model model) {
            String sql = "SELECT * FROM mahasiswa";
            List<Mahasiswa> mahasiswa = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Mahasiswa.class));
            model.addAttribute("mahasiswa", mahasiswa);
            return "index";
        }
     
    @GetMapping(path = {"/tabel_1","/search_1"})
        public String tabel_1( Model model, String keyword) {
            if(keyword!=null){
                String sql = "SELECT * FROM laptop WHERE id_laptop =" + keyword;
                List<Laptop> laptop = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Laptop.class));
                model.addAttribute("laptop", laptop);
                return "tabel_1";

            } else {
                String sql = "SELECT * FROM laptop WHERE deleted=0";
                List<Laptop> laptop = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Laptop.class));
                model.addAttribute("laptop", laptop);
                return "tabel_1";
            }

        }

    @GetMapping(path = {"/tabel_2","/search_2"})
        public String tabel_2(Model model, String keyword) {
            if(keyword!=null){
                String sql = "SELECT * FROM toko WHERE id_toko =" + keyword;
                List<Toko> toko = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Toko.class));
                model.addAttribute("toko", toko);  
                return "tabel_2";

            } else {
                String sql = "SELECT * FROM toko WHERE deleted=0";
                List<Toko> toko = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Toko.class));
                model.addAttribute("toko", toko);            
                return "tabel_2";
            }
            
        }

    @GetMapping(path = {"/tabel_3","/search_3"})
        public String tabel_3(Model model, String keyword) {
            if(keyword!=null){
                String sql = "SELECT * FROM pembeli WHERE id_pembeli =" + keyword;
                List<Pembeli> pembeli = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Pembeli.class));
                model.addAttribute("pembeli", pembeli);            
                return "tabel_3";

            } else {
                String sql = "SELECT * FROM pembeli WHERE deleted=0";
                List<Pembeli> pembeli = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Pembeli.class));
                model.addAttribute("pembeli", pembeli);            
                return "tabel_3";
            }
        }

    @GetMapping(path = {"/tabel_4","/search_4"})
        public String tabel_4(Model model, String keyword) {
            if(keyword!=null){
                String sql = "SELECT * FROM menjual WHERE id_penjualan =" + keyword;
                List<Menjual> menjual = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Menjual.class));
                model.addAttribute("menjual", menjual);            
                return "tabel_4";

            } else {
                String sql = "SELECT * FROM menjual WHERE deleted=0";
                List<Menjual> menjual = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Menjual.class));
                model.addAttribute("menjual", menjual);            
                return "tabel_4";
            }
        }

    
    @GetMapping("/add_1")
        public String add_1(Model model) {
            return "add_1";
        }

    @PostMapping("/add_1")
        public String add_1(Laptop laptop) {
            String sql = "INSERT INTO laptop VALUES(?,?,?,?,0)";
            jdbcTemplate.update(sql, laptop.getId_laptop(), laptop.getNama_laptop(), laptop.getHarga(), laptop.getStok());
            return "redirect:/tabel_1";
        }

    @GetMapping("/add_2")
        public String add_2(Model model) {
            return "add_2";
        }

    @PostMapping("/add_2")
        public String add_2(Toko toko) {
            String sql = "INSERT INTO toko VALUES(?,?,0)";
            jdbcTemplate.update(sql, toko.getId_toko(), toko.getAlamat());
            return "redirect:/tabel_2";
        }

    @GetMapping("/add_3")
        public String add_3(Model model) {
            return "add_3";
        }

    @PostMapping("/add_3")
        public String add_3(Pembeli pembeli) {
            String sql = "INSERT INTO pembeli VALUES(?,?,?,?,0)";
            jdbcTemplate.update(sql, pembeli.getId_pembeli(), pembeli.getNama_pembeli(), pembeli.getEmail(), pembeli.getNo_tlp());
            return "redirect:/tabel_3";
        }

    @GetMapping("/add_4")
        public String add_4(Model model) {
            return "add_4";
        }

    @PostMapping("/add_4")
        public String add_4(Menjual menjual) {
            String sql = "INSERT INTO menjual VALUES(?,?,?,0)";
            jdbcTemplate.update(sql, menjual.getId_penjualan(), menjual.getId_pembeli(), menjual.getId_toko());
            return "redirect:/tabel_4";
        }
        
    @GetMapping("/add")
        public String add(Model model) {
            return "add";
        }

    @PostMapping("/add")
    public String add(Mahasiswa mahasiswa) {
        String sql = "INSERT INTO mahasiswa VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, mahasiswa.getNim(), mahasiswa.getNama(), mahasiswa.getAngkatan(), mahasiswa.getId_tempat());
        return "redirect:/";
    }
    
    @GetMapping("/edit_1/{id_laptop}")
        public String edit_1(@PathVariable("id_laptop") String id_laptop, Model model) {
            String sql = "SELECT * FROM laptop WHERE id_laptop = ?";
            Laptop laptop = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Laptop.class), id_laptop);
            model.addAttribute("laptop", laptop);
            return "edit_1";
        }

    @PostMapping("/edit_1")
        public String edit_1(Laptop laptop) {
            String sql = "UPDATE laptop SET nama_laptop = ?, harga = ?, stok = ? WHERE id_laptop = ?";
            jdbcTemplate.update(sql, laptop.getNama_laptop(), laptop.getHarga(), laptop.getStok(), laptop.getId_laptop());
            return "redirect:/tabel_1";
        }

    @GetMapping("/edit_2/{id_toko}")
        public String edit_2(@PathVariable("id_toko") String id_toko, Model model) {
            String sql = "SELECT * FROM toko WHERE id_toko = ?";
            Toko toko = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Toko.class), id_toko);
            model.addAttribute("toko", toko);
            return "edit_2";
        }

    @PostMapping("/edit_2")
        public String edit_2(Toko toko) {
            String sql = "UPDATE toko SET alamat = ? WHERE id_toko = ?";
            jdbcTemplate.update(sql, toko.getAlamat(), toko.getId_toko());
            return "redirect:/tabel_2";
        }

    @GetMapping("/edit_3/{id_pembeli}")
        public String edit_3(@PathVariable("id_pembeli") String id_pembeli, Model model) {
            String sql = "SELECT * FROM pembeli WHERE id_pembeli = ?";
            Pembeli pembeli = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Pembeli.class), id_pembeli);
            model.addAttribute("pembeli", pembeli);
            return "edit_3";
        }

    @PostMapping("/edit_3")
        public String edit_3(Pembeli pembeli) {
            String sql = "UPDATE pembeli SET nama_pembeli = ?, email = ?, no_tlp = ? WHERE id_pembeli = ?";
            jdbcTemplate.update(sql, pembeli.getNama_pembeli(), pembeli.getEmail(), pembeli.getNo_tlp(), pembeli.getId_pembeli());
            return "redirect:/tabel_3";
        }

    @GetMapping("/edit_4/{id_penjualan}")
        public String edit_4(@PathVariable("id_penjualan") String id_penjualan, Model model) {
            String sql = "SELECT * FROM menjual WHERE id_penjualan = ?";
            Menjual menjual = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Menjual.class), id_penjualan);
            model.addAttribute("menjual", menjual);
            return "edit_4";
        }

    @PostMapping("/edit_4")
        public String edit_4(Menjual menjual) {
            String sql = "UPDATE menjual SET id_pembeli = ?, id_toko = ? WHERE id_penjualan = ?";
            jdbcTemplate.update(sql, menjual.getId_pembeli(), menjual.getId_toko(), menjual.getId_penjualan());
            return "redirect:/tabel_4";
        }
        
    @GetMapping("/edit/{nim}")
        public String edit(@PathVariable("nim") String nim, Model model) {
            String sql = "SELECT * FROM mahasiswa WHERE nim = ?";
            Mahasiswa mahasiswa = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Mahasiswa.class), nim);
            model.addAttribute("mahasiswa", mahasiswa);
            return "edit";
        }

    @PostMapping("/edit")
        public String edit(Mahasiswa mahasiswa) {
            String sql = "UPDATE mahasiswa SET nama = ?, angkatan = ?, id_tempat = ? WHERE nim = ?";
            jdbcTemplate.update(sql, mahasiswa.getNama(), 
            mahasiswa.getAngkatan(), mahasiswa.getId_tempat(), mahasiswa.getNim());
            return "redirect:/";
        }

    @GetMapping("/delete_1/{id_laptop}")
        public String delete_1(@PathVariable("id_laptop") String id_laptop) {
            String sql = "DELETE FROM laptop WHERE id_laptop = ?";
            jdbcTemplate.update(sql, id_laptop);
            return "redirect:/tabel_1";
        }

    @GetMapping("/remove_1/{id_laptop}")
        public String remove_1(@PathVariable("id_laptop") String id_laptop) {
            String sql = "UPDATE laptop SET deleted = 1 WHERE id_laptop = ?";
            jdbcTemplate.update(sql, id_laptop);
            return "redirect:/tabel_1";
        }

    @GetMapping("/delete_2/{id_toko}")
        public String delete_2(@PathVariable("id_toko") String id_toko) {
            String sql = "DELETE FROM toko WHERE id_toko = ?";
            jdbcTemplate.update(sql, id_toko);
            return "redirect:/tabel_2";
        }
    
    @GetMapping("/remove_2/{id_toko}")
        public String remove_2(@PathVariable("id_toko") String id_toko) {
            String sql = "UPDATE toko SET deleted = 1 WHERE id_toko = ?";
            jdbcTemplate.update(sql, id_toko);
            return "redirect:/tabel_2";
        }

    @GetMapping("/delete_3/{id_pembeli}")
        public String delete_3(@PathVariable("id_pembeli") String id_pembeli) {
            String sql = "DELETE FROM pembeli WHERE id_pembeli = ?";
            jdbcTemplate.update(sql, id_pembeli);
            return "redirect:/tabel_3";
        }

    @GetMapping("/remove_3/{id_pembeli}")
        public String remove_3(@PathVariable("id_pembeli") String id_pembeli) {
            String sql = "UPDATE pembeli SET deleted = 1 WHERE id_pembeli = ?";
            jdbcTemplate.update(sql, id_pembeli);
            return "redirect:/tabel_3";
        }

    @GetMapping("/delete_4/{id_penjualan}")
        public String delete_4(@PathVariable("id_penjualan") String id_penjualan) {
            String sql = "DELETE FROM menjual WHERE id_penjualan = ?";
            jdbcTemplate.update(sql, id_penjualan);
            return "redirect:/tabel_4";
        }
    
    @GetMapping("/remove_4/{id_penjualan}")
        public String remove_4(@PathVariable("id_penjualan") String id_penjualan) {
            String sql = "UPDATE menjual SET deleted = 1 WHERE id_penjualan = ?";
            jdbcTemplate.update(sql, id_penjualan);
            return "redirect:/tabel_4";
        }


    @GetMapping("/delete/{nim}")
        public String delete(@PathVariable("nim") String nim) {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            jdbcTemplate.update(sql, nim);
            return "redirect:/";
        }



}

