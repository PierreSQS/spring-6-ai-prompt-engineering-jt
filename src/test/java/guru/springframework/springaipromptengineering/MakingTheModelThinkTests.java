package guru.springframework.springaipromptengineering;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * Modified by Pierrot on 27-10-2025.
 */
@SpringBootTest
class MakingTheModelThinkTests extends BaseTestClass {

    String story = """
        In a charming village, siblings Jack and Jill set out on
        a quest to fetch water from a hilltop well.
        As they climbed, singing joyfully, misfortune
        struck—Jack tripped on a stone and tumbled
        down the hill, with Jill following suit.\s
        Though slightly battered, the pair returned home to
        comforting embraces. Despite the mishap,\s
        their adventurous spirits remained undimmed, and they\s
        continued exploring with delight.
       \s""";

    String prompt = """
            Perform the following actions:
            1 - Summarize the following text delimited by triple
            backticks with 1 sentence.
            2 - Translate the summary into French.
            3 - List each name in the French summary.
            4 - Output a json object that contains the following
            keys: french_summary, num_names.
            Separate your answers with line breaks.
            Text:
            ```{text}```
            """;

    @Test
    void testSteps() {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);

        System.out.println(chatModel.call(promptTemplate
                .create(Map.of("text", story))).getResult().getOutput().getText());

    }

    String prompt2Incorrect = """
            Determine if the student's solution is correct or not.
                       \s
            Question:
            I'm building a solar power installation and I need
             help working out the financials.
            - Land costs $100 / square foot
            - I can buy solar panels for $250 / square foot
            - I negotiated a contract for maintenance that will cost\s
            me a flat $100k per year, and an additional $10 / square foot
           \s
            What is the total cost for the first year of operations
            as a function of the number of square feet.
                       \s
            Student's Solution:
            Let x be the size of the installation in square feet.
            Costs:
            1. Land cost: 100x
            2. Solar panel cost: 250x
            3. Maintenance cost: 100,000 + 100x
            Total cost: 100x + 250x + 100,000 + 100x = 450x + 100,000
           \s""";

    @Test
    void testIncorrectPrompt() {
        PromptTemplate promptTemplate = new PromptTemplate(prompt2Incorrect);

        System.out.println(chatModel.call(promptTemplate.create()).getResult().getOutput().getText());
        System.out.println("\n### the Current Model: "+chatModel.getDefaultOptions().getModel()+" ###");
    }

    String prompt3Correct = """
            Your task is to determine if the student's solution is correct or not.
            To solve the problem do the following:
            - First, work out your own solution to the problem including the final total.
            - Then compare your solution to the student's solution and evaluate if the student's solution is correct or not.
           \s
            Don't decide if the student's solution is correct until you have done the problem yourself.
                       \s
            Use the following format:
            Question:
            ```question here```
           \s
            Student's solution:
            ```student's solution here```
           \s
            Actual solution:
            ```steps to work out the solution and your solution here```
           \s
            Is the student's solution the same as actual solution just calculated:
            ```yes or no```
           \s
            Student grade:
            ```correct or incorrect```
                       \s
            Question:
            ```
            I'm building a solar power installation and I need help working out the financials.
            - Land costs $100 / square foot
            - I can buy solar panels for $250 / square foot
            - I negotiated a contract for maintenance that will cost me a flat $100k per year, and an additional $10 / square foot
           \s
            What is the total cost for the first year of operations as a function of the number of square feet.
            ```
           \s
            Student's solution:
            ```
            Let x be the size of the installation in square feet.
            Costs:
            1. Land cost: 100x
            2. Solar panel cost: 250x
            3. Maintenance cost: 100,000 + 100x
            Total cost: 100x + 250x + 100,000 + 100x = 450x + 100,000
            ```
           \s
            Actual solution:
            ```actual solution here```
           \s
           \s""";

    @Test
    void testCorrectPrompt() {
        PromptTemplate promptTemplate = new PromptTemplate(prompt3Correct);

        System.out.println(chatModel.call(promptTemplate.create()).getResult().getOutput().getText());
    }

    String prompt4 = """
       You are an expert at solving reasoning problems. A cup is an object with an open top and close on the sides and bottom.\s
       The open top does not prevent objects from passing through it.
      \s
       Assume the laws of physics on Earth. A small marble is put into a normal cup and the cup is placed upside down on a\s
       table, causing the open side of the cup to be in contact with the table. Gravity will cause the ball to fall to the table.
       Someone then picks the cup up without changing its orientation and puts it inside the microwave. Where is the ball\s
       now. Determine the position of the ball in each step. Explain\s
       why the ball is positioned where it is.
      \s""";

    @Test
    void testTheBallPrompt() {
        PromptTemplate promptTemplate = new PromptTemplate(prompt4);

        System.out.println(chatModel.call(promptTemplate.create()).getResult().getOutput().getText());
    }

}
