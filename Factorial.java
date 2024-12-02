
class Factorial {
    static int factorial(int n) {
        if (n != 0) // termination condition
            return n * factorial(n - 1); // recursive call
        else
            return 1;

    }
}
