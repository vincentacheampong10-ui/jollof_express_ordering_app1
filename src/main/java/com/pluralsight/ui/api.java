package com.pluralsight.ui;

import org.springframework.web.bind.annotation.*;


public class api {


    public String displayHome() {
        return """
                <pre>
                ╔══════════════════════════════════════════════╗
                ║              JOLLOF EXPRESS MENU             ║
                ╠══════════════════════════════════════════════╣
                ║ 1.  New Order                                ║
                ║ 0.  Exit                                     ║
                ╚══════════════════════════════════════════════╝
                </pre>
                """;
    }
}

