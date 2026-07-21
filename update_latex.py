import re

with open('Android_Lab_Record.tex', 'r') as f:
    content = f.read()

# Add tcolorbox to preamble
content = content.replace('\\usepackage{graphicx}', '\\usepackage{graphicx}\n\\usepackage[most]{tcolorbox}')

# Replace Program 1 Java
p1_java_old = r"""\\multicolumn{2}{\|p\{0.94\\textwidth\}\|}{\s*\\lstinputlisting\[language=Java\]\{code/program1/MainActivity\.java\}\s*\} \\"""
p1_java_new = r"""\\end{longtable}\n\\vspace{-1.5em}\n\\begin{tcolorbox}[breakable, sharp corners, boxrule=0.4pt, colback=white, width=\\linewidth]\n\\lstinputlisting[language=Java]{code/program1/MainActivity.java}\n\\end{tcolorbox}\n\\vspace{-1.5em}\n\\begin{longtable}{|p{0.5\\textwidth}|p{0.42\\textwidth}|}\n\\hline"""
content = re.sub(p1_java_old, p1_java_new, content)

# Replace Program 1 XML
p1_xml_old = r"""\\multicolumn{2}{\|p\{0.94\\textwidth\}\|}{\s*\\lstinputlisting\[language=XML,linerange=\{1-48\}\]\{code/program1/activity_main\.xml\}\s*\} \\\s*\\multicolumn{2}{\|p\{0.94\\textwidth\}\|}{\s*\\lstinputlisting\[language=XML,linerange=\{49-53\},firstnumber=49\]\{code/program1/activity_main\.xml\}\s*\} \\"""
p1_xml_new = r"""\\end{longtable}\n\\vspace{-1.5em}\n\\begin{tcolorbox}[breakable, sharp corners, boxrule=0.4pt, colback=white, width=\\linewidth]\n\\lstinputlisting[language=XML]{code/program1/activity_main.xml}\n\\end{tcolorbox}\n\\vspace{-1.5em}\n\\begin{longtable}{|p{0.5\\textwidth}|p{0.42\\textwidth}|}\n\\hline"""
content = re.sub(p1_xml_old, p1_xml_new, content)

# Replace Program 2 Java
p2_java_old = r"""\\multicolumn{2}{\|p\{0.94\\textwidth\}\|}{\s*\\lstinputlisting\[language=Java,linerange=\{1-45\}\]\{code/program2/MainActivity\.java\}\s*\} \\\s*\\multicolumn{2}{\|p\{0.94\\textwidth\}\|}{\s*\\lstinputlisting\[language=Java,linerange=\{46-48\},firstnumber=46\]\{code/program2/MainActivity\.java\}\s*\} \\"""
p2_java_new = r"""\\end{longtable}\n\\vspace{-1.5em}\n\\begin{tcolorbox}[breakable, sharp corners, boxrule=0.4pt, colback=white, width=\\linewidth]\n\\lstinputlisting[language=Java]{code/program2/MainActivity.java}\n\\end{tcolorbox}\n\\vspace{-1.5em}\n\\begin{longtable}{|p{0.5\\textwidth}|p{0.42\\textwidth}|}\n\\hline"""
content = re.sub(p2_java_old, p2_java_new, content)

# Replace Program 2 XML
p2_xml_old = r"""\\multicolumn{2}{\|p\{0.94\\textwidth\}\|}{\s*\\lstinputlisting\[language=XML,linerange=\{1-46\}\]\{code/program2/activity_main\.xml\}\s*\} \\\s*\\multicolumn{2}{\|p\{0.94\\textwidth\}\|}{\s*\\lstinputlisting\[language=XML,linerange=\{47-76\},firstnumber=47\]\{code/program2/activity_main\.xml\}\s*\} \\"""
p2_xml_new = r"""\\end{longtable}\n\\vspace{-1.5em}\n\\begin{tcolorbox}[breakable, sharp corners, boxrule=0.4pt, colback=white, width=\\linewidth]\n\\lstinputlisting[language=XML]{code/program2/activity_main.xml}\n\\end{tcolorbox}\n\\vspace{-1.5em}\n\\begin{longtable}{|p{0.5\\textwidth}|p{0.42\\textwidth}|}\n\\hline"""
content = re.sub(p2_xml_old, p2_xml_new, content)

with open('Android_Lab_Record.tex', 'w') as f:
    f.write(content)
