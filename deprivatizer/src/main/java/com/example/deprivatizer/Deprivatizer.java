package com.example.deprivatizer;

import java.util.Set;
import java.util.HashSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import com.sun.source.util.TreeScanner;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.VariableTree;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Deprivatizer extends AbstractProcessor
{
  private Trees trees;

  private final Set<JavaFileObject> processed = new HashSet<>();

  @Override
  public void init(ProcessingEnvironment processingEnv)
  {
    super.init(processingEnv);

    trees = Trees.instance(processingEnv);
  }
  
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
  {
    if (trees == null) return false; // TODO: can this happen? should we log a warning?

    for (Element root: roundEnv.getRootElements()) process(root);

    return false;
  }

  private void process(Element element)
  {
    TreePath path = trees.getPath(element);
    if (path == null) return; // TODO: can this happen? should we log a warning?

    CompilationUnitTree unit = path.getCompilationUnit();
    if (unit == null) return; // TODO: can this happen? should we log a warning?

    JavaFileObject file = unit.getSourceFile();
    if (file.getKind() != JavaFileObject.Kind.SOURCE) return; // TODO: can this happen? should we log a warning?

    if (! processed.add(file)) return;

    unit.accept(new DeprivatizeVisitor(), null);
  }

}

class DeprivatizeVisitor extends TreeScanner<Void, Void>
{
  public Void visitVariable(VariableTree node, Void p)
  {
    ((com.sun.tools.javac.tree.JCTree.JCVariableDecl)node).mods.flags &= ~com.sun.tools.javac.code.Flags.PRIVATE;

    return super.visitVariable(node, p);
  }
}