from collections import defaultdict

class StockManager:
    """Handles stock operations: sorting, comparisons, and grouping."""
    
    def __init__(self, stock_dict):
        """Initialize stock dictionary."""
        self.stocks = stock_dict
    
    def min_price(self):
        """Find the stock with the lowest price."""
        return min(self.stocks.items(), key=lambda x: x[1])

    def max_price(self):
        """Find the stock with the highest price."""
        return max(self.stocks.items(), key=lambda x: x[1])

    def sort_by_price(self):
        """Sort stocks by increasing price."""
        return dict(sorted(self.stocks.items(), key=lambda x: x[1]))

    def remove_duplicates(self):
        """Remove duplicate stock entries (same stock name & price)."""
        unique_items = {}
        for key, value in self.stocks.items():
            if key not in unique_items:  
                unique_items[key] = value  
        self.stocks = unique_items  

    def group_by_price_range(self):
        """Group items by price multiples of 500."""
        grouped = defaultdict(list)
        for stock, price in self.stocks.items():
            price_group = (price // 500) * 500  
            grouped[price_group].append(stock)
        return dict(grouped)

    def find_by_price(self, price):
        """Find stock(s) priced at 800."""
        return [stock for stock, p in self.stocks.items() if p == price]

    def compare_with(self, other_stocks):
        """Find unique and mismatched items between two dictionaries."""
        unique_in_first = {k: v for k, v in self.stocks.items() if k not in other_stocks}
        price_mismatch = {k: (v, other_stocks[k]) for k, v in self.stocks.items() if k in other_stocks and v != other_stocks[k]}
        return unique_in_first, price_mismatch


# ==============================
# 🚀 Testing the StockManager Class
# ==============================
def main():
    # First stock dictionary (insertion order maintained)
    stocks1 = {
        "AAPL": 1500, "GOOGL": 2800, "TSLA": 750, "MSFT": 3000,
        "AMZN": 3300, "NFLX": 750, "NVDA": 800, "META": 900
    }

    # Second stock dictionary
    stocks2 = {
        "AAPL": 1500, "GOOGL": 2800, "TSLA": 800, "MSFT": 3200,
        "DIS": 500, "NFLX": 750, "NVDA": 850
    }

    # Create stock managers
    stock_mgr1 = StockManager(stocks1)
    stock_mgr2 = StockManager(stocks2)

    print("\n🔹 Minimum price stock:", stock_mgr1.min_price())
    print("🔹 Maximum price stock:", stock_mgr1.max_price())

    print("\n🔹 Sorted stocks by price (First Dictionary):")
    print(stock_mgr1.sort_by_price())

    # Compare stock dictionaries
    unique_items, mismatched_prices = stock_mgr1.compare_with(stocks2)
    print("\n🔹 Unique stocks in first dictionary:", unique_items)
    print("🔹 Stocks with price mismatches:", mismatched_prices)

    # Remove duplicates from first dictionary
    stock_mgr1.remove_duplicates()
    print("\n🔹 First dictionary after removing duplicates:", stock_mgr1.stocks)

    # Sort both dictionaries
    sorted_stocks1 = stock_mgr1.sort_by_price()
    sorted_stocks2 = stock_mgr2.sort_by_price()
    print("\n🔹 Sorted stocks in first dictionary:", sorted_stocks1)
    print("🔹 Sorted stocks in second dictionary:", sorted_stocks2)

    # Grouping stocks by price multiples of 500
    print("\n🔹 Stocks grouped by price (multiples of 500):")
    print(stock_mgr1.group_by_price_range())

    # Find stocks with price 800
    print("\n🔹 Stocks priced at 800 in first dictionary:", stock_mgr1.find_by_price(800))
    print("🔹 Stocks priced at 800 in second dictionary:", stock_mgr2.find_by_price(800))

if __name__ == "__main__":
    main()
