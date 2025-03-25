"""
2.	Write a discount coupon code using dictionary in Python with different rate coupons for each day of the week.
"""

class DiscountCoupons:
    def __init__(self):
        self.coupons = {
            "Monday": 10,
            "Tuesday": 15,
            "Wednesday": 20,
            "Thursday": 25,
            "Friday": 30,
            "Saturday": 35,
            "Sunday": 40
        }

    def get_discount(self, day):
        return self.coupons.get(day, 0)

    def apply_discount(self, amount, day):
        discount_rate = self.get_discount(day)
        discounted_price = amount - (amount * discount_rate / 100)
        return discount_rate, discounted_price


def main():
    amount = float(input("Enter the purchase amount: $"))
    day = input("Enter the day of the week: ").strip().capitalize()
    
    discount_system = DiscountCoupons()
    
    if day not in discount_system.coupons:
        print("Invalid day entered. Please enter a valid day of the week.")
        return

    discount_rate, final_price = discount_system.apply_discount(amount, day)

    print(f"Day: {day}, Discount applied: {discount_rate}%")
    print(f"Final price after discount: ${final_price:.2f}")


if __name__ == "__main__":
    main()
