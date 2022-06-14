grammar Grammar;
prog:survey;

//Survey
survey: 'ID: ' ID NEWLINE 'Survey' NEWLINE 'Title: ' title=message NEWLINE+ ('Welcome Message:' welcmen=message NEWLINE+)? 'List of Sections:' NEWLINE+ sections=section+ 'Final Message: ' finMess=message #surveystructure;

//Section
section: 'ID: ' ID NEWLINE+ 'Section Title: ' secTit=message NEWLINE+ 'Description:' desc=message NEWLINE+ OBLIGATORINESS NEWLINE REPEATABILITY NEWLINE 'Content:' NEWLINE+ questions=question+ #sections;

//Question
question: 'ID: ' ID NEWLINE+ 'Question: ' quest=message NEWLINE+ ('Instruction:' insttruc=message)?  type=questiontype OBLIGATORINESS NEWLINE ('Extra Information: ' xInfo=message NEWLINE*)? #questions;


//Question Type
questiontype:     'Question Type: ' type=TEXT NEWLINE answer=answers
                | 'Question Type: ' type=OPTION NEWLINE 'Possible Answers:' NEWLINE option answer=answers
                | 'Question Type: ' type=INPUT NEWLINE 'Possible Answers:' NEWLINE option answer=answers
                | 'Question Type: ' type=MULTIPLECHOICEINPUT NEWLINE 'Possible Answers:' NEWLINE option answer=answers
                | 'Question Type: ' type=NUMERIC NEWLINE answer=answers
                | 'Question Type: ' type=SCALINGOPTIONS NEWLINE answer=answers
                | 'Question Type: ' type=DECISION NEWLINE answer=answers
                | 'Question Type: ' type=SORT NEWLINE 'Options:' NEWLINE option answer=answers;

option: (message NEWLINE)+;

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
        |     INT VIRGULA resnumericas;

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