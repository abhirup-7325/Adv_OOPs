def compute_wer(reference_sentence, hypothesis_sentence):
    reference = reference_sentence.strip().split()
    hypothesis = hypothesis_sentence.strip().split()
    
    m, n = len(reference), len(hypothesis)

    if m < n:
        reference, hypothesis = hypothesis, reference
        m, n = n, m

    previous = list(range(n + 1))
    current = [0] * (n + 1)

    for i in range(1, m + 1):
        current[0] = i
        for j in range(1, n + 1):
            cost = 0 if reference[i - 1] == hypothesis[j - 1] else 1
            current[j] = min(
                previous[j] + 1,
                current[j - 1] + 1, 
                previous[j - 1] + cost
            )
        previous, current = current, previous 

    edit_distance = previous[n]

    N = len(reference)
    wer = (edit_distance / N) if N > 0 else 0.0

    print(f"{wer:.4f}")
    
    
def main():
    reference_sentence = input("Enter the reference sentence: ")
    hypothesis_sentence = input("Enter the hypothesis sentence: ")
    
    compute_wer(reference_sentence, hypothesis_sentence)
    
    
if __name__ == "__main__":
    main()