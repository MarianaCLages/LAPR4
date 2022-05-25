grammar GrammarSurvey;

prog: survey;

//Survey
survey: 'Survey - ' WORD+ NEWLINE+ 'ID: ' WORD+ NEWLINE+ section+;

//Section
section: 'Section - ' WORD+ NEWLINE+ 'ID: 'WORD+ NEWLINE+ WORD+ NEWLINE MANDATORY NEWLINE REP NEWLINE question+;

//Question
question: 'Question - ' WORD+ NEWLINE 'ID: ' WORD+ NEWLINE  type MANDATORY NEWLINE WORD+ NEWLINE* ;

//Type
type: 'Type - ' TYPE NEWLINE;

NEWLINE : [\r\n]+ ;
MANDATORY: 'Mandatory'|'Optional';
REP: 'Repeatable' | 'Not-Repeatable';
TYPE: (('Free '|'free ')('text'|'Text'))|('Numeric'|'numeric')|(('Single '|'single '|'Multiple '|'multiple ')('Choice '|'choice ')('with input value')?)|(('Sorting'|'sorting')('Options'|'options'))|(('Scaling'|'scaling')|('Options'|'options'));
INT: [0-9]+;
WORD: [A-Za-z0-9 ?;.|,]+;
JUMP: [ \t]+ -> skip;