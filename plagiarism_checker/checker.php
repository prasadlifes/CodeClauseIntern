<?php
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['text1']) && isset($_POST['text2'])) {
    $text1 = $_POST['text1'];
    $text2 = $_POST['text2'];

    function checkPlagiarism($text1, $text2) {
        $words1 = explode(' ', $text1);
        $words2 = explode(' ', $text2);
        $commonWords = array_intersect($words1, $words2);
        $totalWords = count($words1) + count($words2);
        $similarity = (count($commonWords) * 2) / $totalWords * 100;
        return $similarity;
    }

    $similarity = checkPlagiarism($text1, $text2);
    echo "Similarity: " . $similarity . "%";
} else {
    // Redirect back to the form if the page is accessed without form submission
    header("Location: index.html");
    exit();
}
?>