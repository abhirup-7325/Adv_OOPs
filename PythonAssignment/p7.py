"""
    7.	Write a function findfiles that recursively descends the directory tree for 
    the specified directory and generates paths of all the files in the tree.
    """
    
    
import os

class FileFinder:
    def __init__(self, root_dir):
        self.root_dir = root_dir

    def findfiles(self, directory=None):
        if directory is None:
            directory = self.root_dir

        try:
            for entry in os.listdir(directory):
                full_path = os.path.join(directory, entry)
                if os.path.isfile(full_path):
                    yield full_path
                elif os.path.isdir(full_path):
                    yield from self.findfiles(full_path)
        except PermissionError:
            print(f" Permission denied: {directory}")

def main():
    """Main function to get user input and display file paths."""
    root_directory = input("Enter the directory to search: ").strip()
    
    if not os.path.exists(root_directory):
        print(" Error: Directory does not exist.")
        return
    
    finder = FileFinder(root_directory)

    print("\n📂 Files Found:")
    for file_path in finder.findfiles():
        print(file_path)

if __name__ == "__main__":
    main()
