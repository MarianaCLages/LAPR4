grammar Grammar;
prog:survey;

//Survey
survey: 'ID: ' ID NEWLINE 'Survey' NEWLINE 'Title: ' message NEWLINE+ ('Welcome Message:' message NEWLINE+)? 'List of Sections:' NEWLINE+ section+ 'Final Message: ' message #surveystructure;

//Section
section: 'ID: ' ID NEWLINE+ 'Section Title: ' message NEWLINE+ 'Description:' message NEWLINE+ OBLIGATORINESS NEWLINE REPEATABILITY NEWLINE 'Content:' NEWLINE+ question+ #sections;

//Question
question: 'ID: ' ID NEWLINE+ 'Question: ' message NEWLINE+ ('Instruction:' message)?  questiontype OBLIGATORINESS NEWLINE ('Extra Information: ' message NEWLINE*)? #questions;


//Question Type
questiontype:     'Question Type: ' TEXT NEWLINE answers
                | 'Question Type: ' OPTION NEWLINE 'Possible Answers:' NEWLINE (message NEWLINE)+ answers
                | 'Question Type: ' INPUT NEWLINE 'Possible Answers:' NEWLINE (message NEWLINE)+ answers
                | 'Question Type: ' MULTIPLECHOICEINPUT NEWLINE 'Possible Answers:' NEWLINE (message NEWLINE)+ answers
                | 'Question Type: ' NUMERIC NEWLINE answers
                | 'Question Type: ' SCALINGOPTIONS NEWLINE answers
                | 'Question Type: ' DECISION NEWLINE answers
                | 'Question Type: ' SORT NEWLINE 'Options:' NEWLINE (message NEWLINE)+ answers
                ;

//Answers
answers:
         | ('Answer:' message NEWLINE)
         | ('Answer:' resnumericas NEWLINE)
         | ('Answer:' resnumericas NEWLINE)
         | ('Answer:' DECISIONANSWER NEWLINE)
         | ('Answer:' NEWLINE (message NEWLINE)+);

//Message
message:|MESSAGE
        | message MESSAGE;

//RepsNumericas
resnumericas: INT
        |     INT VIRGULA resnumericas
        ;

NEWLINE : [\r\n]+ ;
OBLIGATORINESS: 'Mandatory'|'Optional' | 'mandatory' | 'optional';
DECISION: 'Decision';
DECISIONANSWER: 'Yes'|'No'|'yes'|'no';
TEXT: 'Free-Text' | 'free-text' | 'Free Text' | 'free text';
REPEATABILITY: 'Repeatable' | 'Not-Repeatable';
NUMERIC: 'Numeric'|'numeric';
OPTION: ('Single '|'single '|'Multiple '|'multiple ')('Choice'|'choice');
SORT: ('Sort '|'sorting ')('Options'|'options');
INPUT: ('Single '|'single ')('Choice '|'choice ');
MULTIPLECHOICEINPUT: ('Multiple' | 'Multiple')('Choice' | 'choice');
SCALINGOPTIONS: ('Scaling ' | 'scaling ')('Options' | 'options');
INT:[0-9]+;
ID:[A-Z0-9]+;
VIRGULA: ',';
MESSAGE:[A-Za-z0-9?;.|, ]+;