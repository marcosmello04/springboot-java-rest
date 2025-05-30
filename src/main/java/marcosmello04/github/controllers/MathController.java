package marcosmello04.github.controllers;

import marcosmello04.github.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // Sum - http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{n1}/{n2}")
    public Double sum(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) throw new UnsupportedMathOperationException("Numeric values only.");
        return toDouble(n1) + toDouble(n2);
    }

    // Subtraction - http://localhost:8080/math/sub/3/5 // How to implement subtraction
    @RequestMapping("/sub/{n1}/{n2}")
    public Double subtraction(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) throw new UnsupportedMathOperationException("Numeric values only.");
        return toDouble(n1) - toDouble(n2);
    }

    // Multiplication - http://localhost:8080/math/mult/3/5
    @RequestMapping("/mult/{n1}/{n2}")
    public Double multiplication(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) throw new UnsupportedMathOperationException("Numeric values only.");
        return toDouble(n1) * toDouble(n2);
    }

    // Division - http://localhost:8080/math/div/3/5
    @RequestMapping("/div/{n1}/{n2}")
    public Double division(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) throw new UnsupportedMathOperationException("Numeric values only.");
        return toDouble(n1) / toDouble(n2);
    }

    // Mean - http://localhost:8080/math/mean/3/5
    @RequestMapping("/mean/{n1}/{n2}")
    public Double mean(@PathVariable("n1") String n1, @PathVariable("n2") String n2) throws Exception {
        if (!isNumeric(n1) || !isNumeric(n2)) throw new UnsupportedMathOperationException("Numeric values only.");
        return (toDouble(n1) + toDouble(n2)) / 2;
    }

    // SquareRoot - http://localhost:8080/math/sqrt/81
    @RequestMapping("/sqrt/{n}")
    public Double squareRoot(@PathVariable("n") String n) throws Exception {
        if (!isNumeric(n)) throw new UnsupportedMathOperationException("Numeric values only.");
        return Math.sqrt(toDouble(n));
    }

    private Double toDouble(String n) throws Exception {
        if (n.matches("[-+]?[0-9]*\\.?[0-9]+"))
            return Double.parseDouble(n.replace(",", "."));
        else throw new UnsupportedMathOperationException("Numeric values only.");
    }

    private boolean isNumeric(String n) {
        return !n.isEmpty();
    }
}
