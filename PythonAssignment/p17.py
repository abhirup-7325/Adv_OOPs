def flatten_dict(nested_dict, parent_key='', separator='.'):
    """
    Recursively flattens a nested dictionary.

    Args:
        nested_dict (dict): The dictionary to flatten.
        parent_key (str): The prefix for nested keys.
        separator (str): The separator between nested keys.

    Returns:
        dict: A flattened dictionary.
    """
    flat_dict = {}
    
    for key, value in nested_dict.items():
        new_key = f"{parent_key}{separator}{key}" if parent_key else key  # Create new key
        
        if isinstance(value, dict):
            # Recursively flatten dictionaries
            flat_dict.update(flatten_dict(value, new_key, separator))
        elif isinstance(value, list):
            # Flatten lists with indices
            for i, item in enumerate(value):
                flat_dict[f"{new_key}[{i}]"] = item
        else:
            flat_dict[new_key] = value  # Store non-dictionary values
            
    return flat_dict

nested = {
    'fullname': 'Alessandra',
    'age': 41,
    'phone-numbers': ['+447421234567', '+447423456789'],
    'residence': {
        'address': {
            'first-line': 'Alexandra Rd',
            'second-line': ''
        },
        'zip': 'N8 0PP',
        'city': 'London',
        'country': 'UK'
    }
}

flattened = flatten_dict(nested)
print("🔹 Flattened Dictionary:\n", flattened)
