import pytest

# Function to compute power
def power(base, exponent):
    return base ** exponent

# ==============================
# 🚀 Parameterized Tests
# ==============================
@pytest.mark.parametrize("base, exponent, expected", [
    (2, 2, 4),
    (2, 3, 8),
    (1, 9, 1),
    (0, 9, 0)
])
def test_power(base, exponent, expected):
    """Test power function with different values."""
    assert power(base, exponent) == expected

# ==============================
# 🚀 Test for Errors
# ==============================
def test_power_invalid():
    """Test for invalid cases like negative exponent for zero."""
    with pytest.raises(ZeroDivisionError):
        power(0, -1)  # Should raise ZeroDivisionError

# Ensure Pytest runs when executing the script
if __name__ == "__main__":
    import sys
    sys.exit(pytest.main(["-v"]))
