package runtime;

import buffer.StringBuffer;
import frontend.AST.Program;
import frontend.AST.Statement;
import frontend.AST.declarations.FunctionDeclaration;
import frontend.AST.declarations.VariableDeclaration;
import frontend.AST.expressions.NumericLiteral;
import runtime.evaluations.ExpressionEvaluator;
import runtime.values.NumberValue;
import frontend.AST.expressions.Identifier;
import frontend.AST.expressions.ObjectLiteral;
import frontend.AST.expressions.CallExpr;
import frontend.AST.expressions.AssignmentExpr;
import frontend.AST.expressions.BinaryExpr;
import runtime.evaluations.Evaluations;

public class Interpreter {
    public static RuntimeValue evaluate(Statement astNode, Environment env) {
        switch (astNode.getKind()) {
            case NUMERIC_LITERAL:
                NumericLiteral numericLiteral = (NumericLiteral) astNode;
                return new NumberValue(numericLiteral.getValue());

            case STRING_LITERAL:
                return new runtime.values.StringValue(((frontend.AST.expressions.StringLiteral) astNode).getValue());

            case IDENTIFIER:
                return ExpressionEvaluator.evaluateIdentifier((Identifier) astNode, env);

            case OBJECT_LITERAL:
                return ExpressionEvaluator.evaluateObjectExpression((ObjectLiteral) astNode, env);

            case CALL_EXPR:
                return ExpressionEvaluator.evaluateCallExpression((CallExpr) astNode, env);

            case ASSIGNMENT_EXPR:
                return ExpressionEvaluator.evaluateAssignment((AssignmentExpr) astNode, env);

            case BINARY_EXPR:
                return ExpressionEvaluator.evaluateBinaryExpression((BinaryExpr) astNode, env);

            case PROGRAM:
                return Evaluations.evaluateProgram((Program) astNode, env);

            case VARIABLE_DECLARATION:
                return Evaluations.evaluateVariableDeclaration((VariableDeclaration) astNode, env);

            case FUNCTION_DECLARATION:
                return Evaluations.evaluateFunctionDeclaration((FunctionDeclaration) astNode, env);

            default:

                System.err.println("Dieser AST-Knoten wurde noch nicht für die Interpretation eingerichtet." + astNode);
                System.exit(1);
                return null;
        }
    }
}
