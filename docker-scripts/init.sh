#!/bin/bash

add_quiz() {
    local data=$1

    local response=$(curl -s -o /dev/null -w "%{http_code}" -X POST \
        http://localhost:8080/api/addQuiz \
        -H 'Content-Type: application/json' \
        -d "$data")

    if [ $response -eq 201 ]; then
        echo "Quiz added successfully."
    else
        echo "Failed to add Quiz. HTTP response code: $response"
    fi
}

# Quiz 1
quiz1_data='{
    "title": "Example flashcard",
    "quizQuestionDTOs": [
        {
            "question": "When II World War started?",
            "answerA": {
                "answer": "1938",
                "correctAnswer": false
            },
            "answerB": {
                "answer": "1940",
                "correctAnswer": false
            },
            "answerC": {
                "answer": "1942",
                "correctAnswer": false
            },
            "answerD": {
                "answer": "1939",
                "correctAnswer": true
            }
        },
        {
            "question": "Which equations equals 4?",
            "answerA": {
                "answer": "7%2",
                "correctAnswer": false
            },
            "answerB": {
                "answer": "2*2*3",
                "correctAnswer": false
            },
            "answerC": {
                "answer": "2+2+2",
                "correctAnswer": false
            },
            "answerD": {
                "answer": "2+2",
                "correctAnswer": true
            }
        }
    ]
}'
add_quiz "$quiz1_data"

# Quiz 2
quiz2_data='{
    "title": "Another example flashcard",
    "quizQuestionDTOs": [
        {
            "question": "When I World War started?",
            "answerA": {
                "answer": "1938",
                "correctAnswer": false
            },
            "answerB": {
                "answer": "1920",
                "correctAnswer": false
            },
            "answerC": {
                "answer": "1921",
                "correctAnswer": false
            },
            "answerD": {
                "answer": "1918",
                "correctAnswer": true
            }
        },
        {
            "question": "How long is decade?",
            "answerA": {
                "answer": "5 years",
                "correctAnswer": false
            },
            "answerB": {
                "answer": "15 years",
                "correctAnswer": false
            },
            "answerC": {
                "answer": "20 years",
                "correctAnswer": false
            },
            "answerD": {
                "answer": "10 years",
                "correctAnswer": true
            }
        }
    ]
}'
add_quiz "$quiz2_data"
