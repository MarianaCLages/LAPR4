grammar GrammarSurvey;

prog: survey+;

//Survey
survey: ID LINE MESSAGE LINE (section  MESSAGE)+;



//Section
section: 'Section\n'  ID LINE MESSAGE LINE MESSAGE LINE OBLIGATORINESS LINE REPEATABLE LINE question+;


//Questions
question:  'Question\n' ID LINE MESSAGE LINE TYPE LINE OBLIGATORINESS LINE MESSAGE LINE;

//INSTRUCTION: SETENCE INSTRUCTION | SETENCE;

TYPE: 'Free-Text '| 'Numeric ' | 'Single-Choice ' | 'Single-Choice with input value ' | 'Multiple-Choice '|'Multiple-Choice with input value '
| 'Sorting Options ' | 'Scaling Options ';
OBLIGATORINESS: 'mandatory '|'optional '|'condition dependent ';
REPEATABLE: | 'repeatable ' | 'non-repeatable ';
//EXTRAINFO: SETENCE EXTRAINFO | SETENCE;


MESSAGE: SETENCE MESSAGE | SETENCE | |;
//Formats
//MESSAGE: SETENCE MESSAGE | SETENCE LINE MESSAGE | SETENCE LINE | SETENCE;

SETENCE: WORD SETENCE | WORD SYMBOL SETENCE | WORD PONTUATION;
ID: (WORD|INT)+;


LINE: [\n]+;
SYMBOL: '#'|'$'|'%'|'&'|'/'|'('|')'|'='|'¿'|'*'|'+'|'-'|'_'|':'|';'|'{'|'}'|'['|']'|'|' |'^'|'~'|'<'|'>'|'@'|'"';
PONTUATION: '!'|'?'|.;
WORD: [A-z]+;
INT: [0-9]+;
DNOT: EOF;