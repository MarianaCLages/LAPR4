grammar Grammar;
prog:survey;

//Survey
survey: 'ID: ' ID NEWLINE 'Survey' NEWLINE 'Title: ' WORD+ NEWLINE+ 'Welcome Message:' WORD+ NEWLINE+ 'List of Sections:' NEWLINE+ section+ 'Final Message: 'WORD+ #surveystructure;

//Section
section: 'ID: 'ID NEWLINE+ 'Section Title: ' WORD+ NEWLINE+ 'Description:' WORD+ NEWLINE+ OBLIGATORINESS NEWLINE REP NEWLINE 'Content:' NEWLINE+ question+ #sections;

//Question
question: 'ID: ' ID NEWLINE+ 'Question: ' WORD+ NEWLINE+ ('Instruction:' WORD+)?  questiontype OBLIGATORINESS NEWLINE 'Extra Information: 'WORD+ NEWLINE* #questions;


//Question Type

questiontype:     'Question Type: ' FREETEXT NEWLINE ('Answer: ' WORD+ NEWLINE)?
                | 'Question Type: ' CHOICE NEWLINE 'Possible Answers:' NEWLINE (WORD+ NEWLINE)+ ('Answer: ' INT NEWLINE)?
                | 'Question Type: ' CHOICEINPUT NEWLINE 'Possible Answers:' NEWLINE (WORD+ NEWLINE)+ ('Answer: ' INT NEWLINE)?
                | 'Question Type: ' MULTIPLECHOICEINPUT NEWLINE 'Possible Answers:' NEWLINE (WORD+ NEWLINE)+ ('Answer: ' WORD+ NEWLINE)?
                | 'Question Type: ' NUMERIC NEWLINE ('ANSWER: ' INT+ NEWLINE)?
                | 'Question Type: ' SCALINGOPTIONS NEWLINE ('Answer: ' INT+ NEWLINE)?
                | 'Question Type: ' DECISION NEWLINE ('Answer: ' DECISIONANSWER NEWLINE)?
                | 'Question Type: ' SORT NEWLINE 'Options:' NEWLINE (WORD+ NEWLINE)+ ('Answer:' NEWLINE (WORD+ NEWLINE)+)?
                ;


NEWLINE : [\r\n]+ ;
OBLIGATORINESS: 'Mandatory'|'Optional' | 'mandatory' | 'optional';
DECISION: 'Decision';
DECISIONANSWER: 'Yes'|'No'|'yes'|'no';
FREETEXT: 'Free-Text' | 'free-text';
REP: 'Repeatable' | 'Not-Repeatable';
NUMERIC: 'Numeric'|'numeric';
CHOICE: ('Single '|'single '|'Multiple '|'multiple ')('Choice'|'choice');
SORT: ('Sort '|'sorting ')('Options'|'options');
CHOICEINPUT: ('Single '|'single ')('Choice '|'choice ');
MULTIPLECHOICEINPUT: ('Multiple' | 'Multiple')('Choice' | 'choice');
SCALINGOPTIONS: ('Scaling ' | 'scaling ')('Options' | 'options');
INT:[0-9]+;
ID:[A-Z]+[0-9]+;
WORD:[A-Za-z0-9?;.|,]+;
WS: [ \t]+ -> skip;