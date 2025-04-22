def customSort(items):
    items.sort(key = lambda x: x[1])
        
        
def main():
    items = [
        ("apple", 2),
        ("banana", 1),
        ("cherry", 3)
    ]
    
    print("Before sorting:", items)
    customSort(items)
    print("After sorting:", items)
        
        
if __name__ == "__main__":
    main()