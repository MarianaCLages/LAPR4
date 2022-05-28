grammar Grammar;
prog:survey;

//Survey
survey: 'ID: ' ID LINE 'Survey' LINE 'Title: ' MESSAGE+ LINE+ 'Welcome Message:' MESSAGE+ LINE+ 'List of Sections:' LINE+ section+ 'Final Message: 'MESSAGE+ #surveystructure;

//Section
section: 'ID: 'ID LINE+ 'Section Title: ' MESSAGE+ LINE+ 'Description:' MESSAGE+ LINE+ OBLIGATORINESS LINE REPEATABLE LINE 'Content:' LINE+ question+ #sections;

//Question
question: 'ID: ' ID LINE+ 'Question: ' MESSAGE+ LINE+ ('Instruction:' MESSAGE+)?  questiontype OBLIGATORINESS LINE 'Extra Information: 'MESSAGE+ LINE* #questions;


//Question Type

questiontype:     'Question Type: ' TEXT LINE answers?
                | 'Question Type: ' OPTION LINE 'Possible Answers:' LINE (MESSAGE+ LINE)+ answers?
                | 'Question Type: ' INPUT LINE 'Possible Answers:' LINE (MESSAGE+ LINE)+ answers?
                | 'Question Type: ' MULTIPLECHOICEINPUT LINE 'Possible Answers:' LINE (MESSAGE+ LINE)+ answers?
                | 'Question Type: ' NUMERIC LINE answers?
                | 'Question Type: ' SCALINGOPTIONS LINE answers?
                | 'Question Type: ' DECISION LINE answers?
                | 'Question Type: ' SORT LINE 'Possibilities:' LINE (MESSAGE+ LINE)+ answers?
                ;

answers:   ('Answer: ' MESSAGE+ LINE)
         | ('Answer: ' INT LINE)
         | ('ANSWER: ' INT+ LINE)
         | ('Answer: ' DECISIONANSWER LINE)
         | ('Answer:' LINE (MESSAGE+ LINE)+);

LINE : [\r\n]+ ;
OBLIGATORINESS: 'Mandatory'|'Optional' | 'mandatory' | 'optional';
DECISION: 'Decision';
DECISIONANSWER: 'Yes'|'No'|'yes'|'no';
TEXT: 'Free-Text' | 'free-text' | 'Free Text' | 'free text';
REPEATABLE: 'Repeatable' | 'Not-Repeatable';
NUMERIC: 'Numeric'|'numeric';
OPTION: ('Single '|'single '|'Multiple '|'multiple ')('Choice'|'choice');
SORT: ('Sort '|'sorting ')('Options'|'options');
INPUT: ('Single '|'single ')('Choice '|'choice ');
MULTIPLECHOICEINPUT: ('Multiple' | 'Multiple')('Choice' | 'choice');
SCALINGOPTIONS: ('Scaling ' | 'scaling ')('Options' | 'options');
INT:[0-9]+;
ID:[A-Z0-9]+;
MESSAGE:[A-Za-z0-9?;.|, ]+;