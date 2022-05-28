grammar Grammar;
prog: survey ;

//Survey
survey: 'Survey - ' WORD+ NEWLINE+ 'ID: ' WORD+ NEWLINE+ section+ #questionnaire;

//Section
section: 'Section - ' WORD+ NEWLINE+ 'ID: 'WORD+ NEWLINE+ MANDATORY NEWLINE REP NEWLINE question+ #sections;

//Question
question: 'Question - ' WORD+ NEWLINE 'ID: ' WORD+ NEWLINE  type MANDATORY NEWLINE WORD+ NEWLINE* #questions;

//Type
type:       'Type: ' FREETEXT NEWLINE ('Answer: ' WORD+ NEWLINE)?
          | 'Type: ' NUM NEWLINE ('Answer: ' INT+ NEWLINE)?
          | 'Type: ' CHOICE NEWLINE 'Options:' NEWLINE (WORD+ NEWLINE)+ ('Answer: ' INT NEWLINE)?
          | 'Type: ' SORT NEWLINE 'Options: ' NEWLINE (WORD+ NEWLINE)+ ('Answer: ' INT+ NEWLINE)?
          | 'Type: ' CHOICEINPUT NEWLINE 'Options:' NEWLINE (WORD+ NEWLINE)+ ('Other'|'other') NEWLINE ('Answer: ' INT NEWLINE)?
      ;

NEWLINE : [\r\n]+ ;
MANDATORY: 'Mandatory'|'Optional';
DECISION: 'yes'|'no';
FREETEXT: ('Free '|'free ')('text'|'Text');
REP: 'Repeatable' | 'Not-Repeatable';
NUM: 'Numeric'|'numeric';
CHOICE: (('Single '|'single '|'Multiple '|'multiple ')('Choice'|'choice'))|(('Scaling '|'scaling ')|('Options'|'options'));
SORT: ('Sort '|'sorting ')('Options'|'options');
CHOICEINPUT: ('Single '|'single '|'Multiple '|'multiple ')('Choice '|'choice ');
INT:[0-9]+;
ID:[A-Z]+[0-9]+;
WORD:[A-Za-z0-9?;.|,]+;
WS: [ \t]+ -> skip;