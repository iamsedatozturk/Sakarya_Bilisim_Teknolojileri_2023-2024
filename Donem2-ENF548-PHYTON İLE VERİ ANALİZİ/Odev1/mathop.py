def add(a, b):
    """İki sayıyı toplar."""
    return a + b

def subtract(a, b):
    """İki sayıdan birini diğerinden çıkarır."""
    return a - b

def multiply(a, b):
    """İki sayıyı çarpar."""
    return a * b

def divide(a, b):
    """İki sayıyı böler."""
    if b == 0:
        return "Hata: Sıfıra bölme hatası."
    return a / b