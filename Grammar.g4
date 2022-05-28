grammar Grammar;
prog:survey;

//Survey
survey: 'Survey: ' WORD+ NEWLINE+ 'ID: ' ID NEWLINE+ section+ 'Final Message: 'WORD+ #surveystructure;

//Section
section: 'Section: ' WORD+ NEWLINE+ 'ID: 'ID NEWLINE+ MANDATORY NEWLINE REP NEWLINE 'Content: ' NEWLINE question+ #sections;

//Question
question: 'Question: ' WORD+ NEWLINE 'ID: ' ID NEWLINE  type MANDATORY NEWLINE 'Extra Information: 'WORD+ NEWLINE* #questions;

//Type
type:       'Question Type: ' TEXT NEWLINE ('Answer: ' WORD+ NEWLINE)?
          | 'Question Type: ' NUM NEWLINE ('Answer: ' INT+ NEWLINE)?
          | 'Question Type: ' CHOICE NEWLINE 'Possible Answers: ' NEWLINE (WORD+ NEWLINE)+ ('Answer: ' INT NEWLINE)?
          | 'Question Type: ' SORT NEWLINE 'Possible Answers: ' NEWLINE (WORD+ NEWLINE)+ ('Answer: ' INT+ NEWLINE)?
          | 'Question Type: ' CHOICEINPUT NEWLINE 'Possible Answers: ' NEWLINE (WORD+ NEWLINE)+ ('Answer: ' INT NEWLINE)?
      ;

NEWLINE : [\r\n]+ ;
MANDATORY: 'Mandatory'|'Optional' | 'mandatory' | 'optional';
DECISION: 'yes'|'no' | 'Yes' | 'No';
TEXT: 'text'|'Text';
REP: 'Repeatable' | 'Not-Repeatable';
NUM: 'Numeric'|'numeric';
CHOICE: ('Single '|'single '|'Multiple '|'multiple ')('Choice'|'choice');
SORT: ('Sort '|'sorting ')('Options'|'options');
CHOICEINPUT: ('Single '|'single '|'Multiple '|'multiple ')('Choice '|'choice ');
INT:[0-9]+;
ID:[A-Z]+[0-9]+;
WORD:[A-Za-z0-9?;.|,]+;
WS: [ \t]+ -> skip;