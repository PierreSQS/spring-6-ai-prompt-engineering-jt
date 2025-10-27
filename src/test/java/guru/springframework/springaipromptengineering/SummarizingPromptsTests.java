package guru.springframework.springaipromptengineering;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * Modified by Pierrot on 27-10-2025.
 */
@SpringBootTest
class SummarizingPromptsTests extends BaseTestClass {

    String review1 = """
            "Elon Musk" by Walter Isaacson is an extraordinary biographical exploration of one of the most fascinating and\s
            innovative figures of our time. As an admirer of Elon Musk and his ventures, I found this book to be an incredibly\s
            insightful and inspiring read that goes far beyond the typical biography. Here's why I believe it's a must-read for\s
            anyone interested in technology, entrepreneurship, and the future of humanity.
                       \s
            Thorough and In-Depth Research:
            Walter Isaacson is renowned for his meticulous research and ability to provide a comprehensive account of his\s
            subjects. In "Elon Musk," he delves deep into Musk's life, from his childhood in South Africa to his founding of\s
            multiple groundbreaking companies like SpaceX and Tesla. The book leaves no stone unturned, offering a detailed\s
            and well-rounded portrait of this visionary entrepreneur.
                       \s
            Humanizing the Genius:
            Isaacson's writing shines in its ability to humanize Musk, a man often seen as an enigmatic genius. The book\s
            delves into Musk's personal struggles, his successes, and his vulnerabilities, allowing readers to relate to\s
            him on a human level. This approach makes the story all the more engaging and relatable.
                       \s
            Awe-Inspiring Vision:
            Musk's vision for the future is nothing short of awe-inspiring, and Isaacson does an exceptional job of conveying\s
            the magnitude of Musk's ambitions. From colonizing Mars to revolutionizing the automotive industry, Musk's visionary\s
            ideas are portrayed with enthusiasm and intellectual depth. Reading about his endeavors leaves you feeling\s
            invigorated and excited about the possibilities of our future.
                       \s
            Insights into the Creative Process:
            "Elon Musk" offers valuable insights into the creative process of a brilliant mind. The book details Musk's\s
            relentless pursuit of innovation and his willingness to take risks that others deemed impossible. For aspiring\s
            entrepreneurs and innovators, the book provides a treasure trove of lessons on perseverance, problem-solving,\s
            and thinking beyond conventional boundaries.
                       \s
            Compelling Narrative Style:
            Walter Isaacson's storytelling skills are evident throughout the book. His ability to craft a compelling\s
            narrative makes this biography read more like an adventure novel. The prose flows seamlessly, keeping the reader
            engaged and eager to turn the page.
                       \s
            Timely and Relevant:
            In an era where technology and the future of our planet are at the forefront of global discussions, "Elon Musk"\s
            is incredibly timely and relevant. The book not only provides a window into Musk's life but also addresses\s
            pressing issues like sustainable energy, space exploration, and artificial intelligence.
                       \s
            In conclusion, "Elon Musk" by Walter Isaacson is an exceptional biography that offers a profound and intimate\s
            look at the life and mind of a modern visionary. It's a testament to the power of human determination, innovation,\s
            and audacious dreams. Whether you're an Elon Musk enthusiast or simply curious about the world-changing ideas\s
            of our time, this book is a captivating and enlightening journey that is not to be missed. I highly recommend\s
            it as a must-read for anyone seeking inspiration and insight into the future.""";

    String reviewPrompt = """
        Your task is to generate a short summary for a book from an ecommerce site. The summary will be used for a
        web page selling the book.

        Summarize the review below, delimited by triple backticks, in at most 30 words.
    
        Review: ```{review}```
    """;

    @Test
    void testCreateDescriptionFromReview() {
        PromptTemplate promptTemplate = new PromptTemplate(reviewPrompt);

        System.out.println(chatModel.call(promptTemplate.create(Map.of("review", review1))).getResult().getOutput().getText());
    }

    String review2 = """
            I finally had the chance to read Walter Isaacson’s latest book on Elon Musk over the holidays. This book is\s
            more than just a biography; it offers a masterclass in the mindset and process of a tech revolutionary who\s
            challenges the status quo and redefines what's possible.
                        \s
            This engaging read delves into Musk's innovative work - from space exploration and sleek electric car designs\s
            to satellite internet and AI advancements. The narrative provides an insight into Musk's thought process, highlighting\s
            his strategic thinking, learn-by-trying approach, problem-solving skills, and bold decision-making.
                        \s
            Isaacson's approach is both educational and inspiring, simplifying the details of Musk's BIG-scale projects while\s
            maintaining the key elements of their groundbreaking impact. The book transforms his sequential innovation\s
            into a practical guide for the art of possibility exploration and idea development, accessible to readers\s
            from all walks of life.
                        \s
            The structure of the book, with short and readable chapters, enhances understanding and keeps you engaged.\s
            Isaacson’s thorough research and extensive interviews unlock the deeper significance of Musk's projects, beyond\s
            just technology. It enables readers to abstract the complexity of his work and extract valuable lessons\s
            applicable to business, the creative thought process, and even personal growth.
                        \s
            While the book is comprehensive, I wish it had delved deeper into Elon Musk's insights on AI. Given his pivotal\s
            roles in OpenAI and xAI GROK, readers would find the book even more valuable with a more extensive exploration\s
            of Musk's perspectives on AI.
           \s""";

    String review3 = """
            An excellent biography of an exceptional person. Elon Musk has been incredibly successful is diverse directions.
            This book gave insight into what has driven him. Like Steve Jobs, Musk is absolutely focused on the end product
            with minimal concern about the path. Musk is not satisfied when a product merely meets its initial specifications;\s
            it must also accomplish that by the most efficient means. And he doesn't fear taking risks along the way.
            It seems impossible that a single person could have accomplished what Musk has done. This book goes a long way to\s
            reveal how he came to be the way he is, how he operates and what drives him. It would be very hard to live with\s
            such a person and this seems fairly well documented. The purchase of Twitter/X is particularly interesting- his\s
            end goal was to end the 'woke' movement and encourage 'free speech', but things got complicated, and not helped\s
            by Musk's propensity to do stupid things (a recurring theme).
            The world is very lucky to have Elon Musk. But its complicated..
            Anyhow, reading this well written book provides insight to one of the most productive people of our time.\s
            I recommend it highly.""";

    String reviewPrompt3 = """
        Your task is to generate a summary for a book from reviews. The summary will be used for a
        web page selling the book. You will be given 3 reviews. Create the summary based on the reviews and
        include information in the summary from all 3 reviews.

        Summarize the reviews below, delimited by triple backticks, in at most 200 words.
   \s
        Review: ```{review}```
       \s
        Review 2: ```{review2}```
       \s
        Review 3: ```{review3}```
   \s""";

    @Test
    void testCreateDescriptionFrom3Reviews() {
        PromptTemplate promptTemplate = new PromptTemplate(reviewPrompt3);

        System.out.println(chatModel.call(promptTemplate
                .create(Map.of("review", review1, "review2", review2, "review3", review3)))
                .getResult().getOutput().getText());
    }

    String reviewPrompt4 = """
        Your task is to extract a summary for a book from reviews. The summary will be used for a
        web page selling the book. You will be given 3 reviews. Create the summary based on the reviews and
        include information in the summary from all 3 reviews.

        Summarize the reviews below, delimited by triple backticks, in at most 200 words.
   \s
        Review: ```{review}```
       \s
        Review 2: ```{review2}```
       \s
        Review 3: ```{review3}```
   \s""";

    @Test
    void testCreateDescriptionFrom3ReviewsExtract() {
        PromptTemplate promptTemplate = new PromptTemplate(reviewPrompt4);

        System.out.println(chatModel.call(promptTemplate
                .create(Map.of("review", review1, "review2", review2, "review3", review3)))
                .getResult().getOutput().getText());
    }
}
